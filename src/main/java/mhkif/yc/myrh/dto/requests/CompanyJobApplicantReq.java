package mhkif.yc.myrh.dto.requests;

import mhkif.yc.myrh.enums.JobApplicationStatus;
import lombok.Data;

@Data
public class CompanyJobApplicantReq {

    private int companyId;
    private int offerId;
    private int jobSeekerId;
    private JobApplicationStatus status;
}
