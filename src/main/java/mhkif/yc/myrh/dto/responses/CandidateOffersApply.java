package mhkif.yc.myrh.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateOffersApply {
    private int offerId;
    private String offerTitle;
}