package mhkif.yc.myrh.dto;

import mhkif.yc.myrh.enums.SubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanySubscribeRequest {
    String companyId;
    SubscriptionStatus subscriptionStatus;
    String token;
}
