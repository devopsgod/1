package by.vstu.model.abiturient;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Embeddable
public class CompetitionInfo {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "abiturient")
    private Set<Document> documents;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "abiturient")
    @OrderBy("order ASC")
    private Set<AbiturientSpeciality> specialities;
}
