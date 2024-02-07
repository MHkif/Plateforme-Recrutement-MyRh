package mhkif.yc.myrh.dto.requests;

import mhkif.yc.myrh.model.Company;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RecruiterReq {


    @NotBlank(message = "First name field is required")
    @Size(message = "Image must not be more than 50", min = 5, max = 54)
    private String first_name;

    @NotBlank(message = "Last name field is required")
    @Size(message = "Last name must not be more than 50", min = 5, max = 54)
    private String last_name;

    @NotNull(message = "Email field is required")
    @NotBlank(message = "Email field must not be blank")
    @Size(message = "Image must not be more than 50", min = 5, max = 54)
    private String email;

    @NotBlank(message = "Password field is required")
    @Size(message = "Password must not be more than 50", min = 5, max = 54)
    private String password;

    @NotBlank(message = "Image field is required")
    @Size(message = "Image must not be more than 50", min = 5, max = 54)
    private String image;

    @NotNull(message = "Company field is required")
    //@NotBlank(message = "Company field must not be blank")
    //@Size(message = "Company must not be more than 50")
    private Company company;
    private boolean isEnabled;


}
