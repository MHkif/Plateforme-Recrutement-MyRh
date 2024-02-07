package mhkif.yc.myrh.dto.requests;

import mhkif.yc.myrh.enums.JobApplicationStatus;
import mhkif.yc.myrh.model.JobApplicantId;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Valid
public class JobApplicantReq {

    private JobApplicantId id;
    private LocalDateTime createdDate = LocalDateTime.now();
    private JobSeekerReq jobSeeker;
    private MultipartFile resume;
    private Boolean isViewed = false;
    private JobApplicationStatus status = JobApplicationStatus.WAITING;
}
