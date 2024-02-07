package mhkif.yc.myrh.model;

import mhkif.yc.myrh.enums.OfferStatus;
import mhkif.yc.myrh.enums.StudyLevel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    private Company company;
    @ManyToOne
    private ActivityArea profile;
    @ManyToOne
    private City city;
    @Enumerated(EnumType.STRING)
    private StudyLevel level;
    @Enumerated(EnumType.STRING)
    private OfferStatus status = OfferStatus.PENDING;

    // @ManyToMany annotation is used on both the entities but only one entity can be the owner of the relationship.
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "JobApplicant",
            joinColumns = { @JoinColumn(name = "offer_id") },
            inverseJoinColumns = { @JoinColumn(name = "jobSeeker_id") })
    Set<JobSeeker> jobSeekers = new HashSet<>();
    private float salary;
    private String image;
}
