package mhkif.yc.myrh.dto.responses;

import mhkif.yc.myrh.enums.OfferStatus;
import mhkif.yc.myrh.enums.StudyLevel;
import lombok.Data;

@Data
public class OfferRes {

    private int id;
    private String title;
    private String description;
    private CompanyRes company;
    private ActivityAreaRes profile;
    private CityRes city;
    private StudyLevel level;
    private OfferStatus status;
    private float salary;


}
