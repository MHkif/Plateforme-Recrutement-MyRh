package mhkif.yc.myrh.model;

import lombok.Builder;
import mhkif.yc.myrh.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "JobSeeker")
public class JobSeeker extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private RoleEnum role = RoleEnum.APPLICANT;
    @ManyToOne
    private  Profile profile;

    private Boolean profile_verify;
    // The entity that specifies the @JoinTable is the owning side of the relationship and
    // the entity that specifies the mappedBy attribute is the inverse side.
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "jobSeekers"
    )

   Set<Offer> offers = new HashSet<>();


}
