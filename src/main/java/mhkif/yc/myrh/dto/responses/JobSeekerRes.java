package mhkif.yc.myrh.dto.responses;

import lombok.Data;
import mhkif.yc.myrh.model.Profile;


@Data
public class JobSeekerRes {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String image;
    private Boolean profile_verify;
    private ProfileRes profile;
    private boolean isEnabled;
    //Set<Offer> offers = new HashSet<>();

}
