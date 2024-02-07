package mhkif.yc.myrh.dto.responses;


import lombok.Data;

@Data
public class RecruiterRes {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String image;
    private CompanyRes company;
    private boolean isEnabled;

}
