package mhkif.yc.myrh.model;

import mhkif.yc.myrh.enums.UserStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Person {

    protected String first_name;
    protected String last_name;
    protected String email;
    protected String password;
    protected String image;
    protected boolean isEnabled;
    @Enumerated(EnumType.STRING)
    protected UserStatus status;




}
