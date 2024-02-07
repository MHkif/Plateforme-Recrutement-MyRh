package mhkif.yc.myrh.dto.requests;

import lombok.Data;

@Data
public class AdminReq {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String image;

}
