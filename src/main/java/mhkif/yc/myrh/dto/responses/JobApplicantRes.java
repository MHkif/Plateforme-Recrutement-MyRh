package mhkif.yc.myrh.dto.responses;

import mhkif.yc.myrh.enums.JobApplicationStatus;
import mhkif.yc.myrh.model.JobApplicantId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobApplicantRes {
    private JobApplicantId id;
    private LocalDateTime createdDate = LocalDateTime.now();
    private JobSeekerRes jobSeeker;
    private OfferRes offer;
    private String resume;
    private Boolean isViewed;
    private JobApplicationStatus status;
}
