package mhkif.yc.myrh.dto.requests;

import mhkif.yc.myrh.enums.OfferStatus;
import mhkif.yc.myrh.enums.StudyLevel;
import mhkif.yc.myrh.model.ActivityArea;
import mhkif.yc.myrh.model.City;
import mhkif.yc.myrh.model.Company;
import lombok.Data;

@Data
public class OfferReq {
    private String title;
    private String description;
    private Company company;
    private ActivityArea profile;
    private City city;
    private StudyLevel level;
    private OfferStatus status = OfferStatus.PENDING;
    private float salary;
}
