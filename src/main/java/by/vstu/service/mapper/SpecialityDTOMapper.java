package by.vstu.service.mapper;

import by.vstu.dto.dictionary.speciality.FacultyDTO;
import by.vstu.dto.dictionary.speciality.SpecialityDTO;
import by.vstu.dto.dictionary.speciality.SpecialityGroupDTO;
import by.vstu.model.dictionary.competition.Speciality;
import by.vstu.service.dictionary.FacultyService;
import by.vstu.service.dictionary.SpecialityGroupService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpecialityDTOMapper implements EntityToDTOMapper<SpecialityDTO, Speciality, SpecialityDTO> {

    private final ModelMapper mapper = new ModelMapper();

    private final SpecialityGroupService specialityGroupService;
    private final FacultyService facultyService;

    @Override
    public SpecialityDTO toDTO(Speciality entity, Object... args) {
        SpecialityDTO doo = mapper.map(entity, SpecialityDTO.class);
        doo.setName(entity.getName().concat(entity.typeToString()));
        doo.setFaculty(mapper.map(entity.getFaculty(), FacultyDTO.class));
        doo.setGroup(mapper.map(entity.getGroup(), SpecialityGroupDTO.class));
        return doo;
    }

    @Override
    public Speciality toEntity(SpecialityDTO dto, Object... args) {
        Speciality speciality = mapper.map(dto, Speciality.class);
        speciality.setFaculty(facultyService.findByIdNotNull(dto.getFacultyId()));
        speciality.setGroup(specialityGroupService.findByIdNotNull(dto.getGroupId()));
        return speciality;
    }
}
