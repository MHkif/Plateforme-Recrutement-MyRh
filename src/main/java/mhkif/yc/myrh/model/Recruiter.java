package mhkif.yc.myrh.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Recruiter extends Person {
    //@Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@ManyToOne
    private Company company;

   // @OneToMany(mappedBy = "recruiter")
    private List<Offer> offers = new ArrayList<>();
}
