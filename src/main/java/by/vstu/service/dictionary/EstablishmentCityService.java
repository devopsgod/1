package by.vstu.service.dictionary;

import by.vstu.dto.dictionary.education.EstablishmentCityDTO;
import by.vstu.exception.BusinessException;
import by.vstu.exception.EntityNotFoundException;
import by.vstu.model.dictionary.education.EstablishmentCity;
import by.vstu.repository.dictionary.EstablishmentCityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstablishmentCityService {

    private final EstablishmentCityRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public Page<EstablishmentCityDTO> findByNamePageable(String name, Pageable pageable) {
        return repository.findByNameIgnoreCaseContaining(name, pageable)
                .map(entity -> mapper.map(entity, EstablishmentCityDTO.class));
    }

    public EstablishmentCityDTO create(EstablishmentCityDTO cityDIO) {
        checkNameExists(cityDIO.getName());
        EstablishmentCity city = repository.save(mapper.map(cityDIO, EstablishmentCity.class));
        return mapper.map(city, EstablishmentCityDTO.class);
    }

    public EstablishmentCityDTO update(Long id, EstablishmentCityDTO cityDIO) {
        EstablishmentCity city = findByIdNotNull(id);
        city.setName(cityDIO.getName());
        return mapper.map(repository.save(city), EstablishmentCityDTO.class);
    }


    public EstablishmentCity findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Establishment city with id " + id + " not found"));
    }

    private void checkNameExists(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException("Region with name=" + name + " exist");
        }
    }
}
