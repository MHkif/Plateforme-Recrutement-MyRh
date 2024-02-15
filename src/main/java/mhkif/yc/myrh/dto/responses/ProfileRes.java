package mhkif.yc.myrh.dto.responses;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import mhkif.yc.myrh.model.ActivityArea;
@Data
public class ProfileRes {
    private int id;
    private String name;
    private ActivityAreaRes activityArea;
}
