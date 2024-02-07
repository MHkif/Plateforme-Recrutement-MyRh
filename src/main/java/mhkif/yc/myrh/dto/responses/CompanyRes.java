package mhkif.yc.myrh.dto.responses;

import lombok.Data;

@Data
public class CompanyRes {
    private int id;
    private String name;
    private String email;
    private String password;
    private String image;
    private  boolean enabled;

    @Override
    public String toString() {
        return " => {"+
                "id : "+ this.id+
                "name : "+ this.name+
                "email : "+ this.email+
                "password : "+ this.password+
                "image : "+ this.image+
                "enabled : "+ this.enabled+
                "}";
    }
}
