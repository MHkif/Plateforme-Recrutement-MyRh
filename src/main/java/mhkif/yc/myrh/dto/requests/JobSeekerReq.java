package mhkif.yc.myrh.dto.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JobSeekerReq {
    @Valid

    @NotNull(message = "first name field is required")
    @NotBlank(message = "first name field is required")
    @Size(message = "first name must not be less than 3 & more than 30 chars", min = 3, max = 30)
    private String first_name;

    @NotNull(message = "last name field is required")
    @NotBlank(message = "last name field is required")
    @Size(message = "last name must not be less than 3 & more than 30 chars", min = 3, max = 30)
    private String last_name;

    @NotNull(message = "email field is required")
    @NotBlank(message = "email field is required")
    @Size(message = "email must not be less than 10 & more than 30 chars", min = 3, max = 30)
    private String email;

    @NotNull(message = "password field is required")
    @NotBlank(message = "password field is required")
    @Size(message = "password must not be less than 10 & more than 30 chars", min = 3, max = 30)
    private String password;



    private String image;


}
