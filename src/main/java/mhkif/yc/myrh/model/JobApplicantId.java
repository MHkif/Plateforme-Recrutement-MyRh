package mhkif.yc.myrh.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicantId implements Serializable {
    private static final long serialVersionUID = 1L;

    @MapsId("offer_id")
    private Integer offer_id;
    @MapsId("jobSeeker_id")
    private Integer jobSeeker_id;
}
