package mhkif.yc.myrh.model;

import mhkif.yc.myrh.enums.JobApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicant {
    @EmbeddedId
    private JobApplicantId id ;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();
    private String resume;
    private Boolean isViewed;
    @Enumerated(EnumType.STRING)
    private JobApplicationStatus status;

}
