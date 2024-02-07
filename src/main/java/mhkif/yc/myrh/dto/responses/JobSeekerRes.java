package mhkif.yc.myrh.dto.responses;

import lombok.Data;


@Data
public class JobSeekerRes {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String image;
    private boolean isEnabled;
    //Set<Offer> offers = new HashSet<>();

}
