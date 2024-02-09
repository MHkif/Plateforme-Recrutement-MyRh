package mhkif.yc.myrh.dto.requests;

import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mhkif.yc.myrh.model.ActivityArea;

@Data

public class ProfileReq {
    @Valid

    @NotNull(message = "name field is required")
    @NotBlank(message = "name field is required")
    private String name;
    @NotNull(message = "activityArea field is required")
    @NotBlank(message = "activityArea field is required")
    private ActivityArea activityArea;
}
