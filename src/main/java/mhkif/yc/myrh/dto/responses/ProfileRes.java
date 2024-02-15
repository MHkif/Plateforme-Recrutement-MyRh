package mhkif.yc.myrh.dto.responses;


import lombok.Data;

@Data
public class ProfileRes {
    private int id;
    private String name;
    private ActivityAreaRes activityArea;
}
