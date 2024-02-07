package mhkif.yc.myrh.dto.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthReq {
    @Valid

    @NotNull(message = "email field is required")
    @NotBlank(message = "email field is required")
    @Size(message = "Email must not be less than 10 & more than 30 chars", min = 10, max = 30)
    private String email;

    @NotNull(message = "password field is required")
    @NotBlank(message = "password field is required")
    @Size(message = "Password must not be less than 6 & more than 26 chars", min = 6, max = 26)
    private String password;
}
