package mhkif.yc.myrh.dto.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompanyReq {

    @Valid
    @NotNull(message = "name field is required")
    @NotBlank(message = "name field is required")
    @Size(message = "Name must not be more than 50 chars", min = 5, max = 54)
    private String name;
    private String email;
    private String password;
    private String image;
    private boolean enabled;


}
