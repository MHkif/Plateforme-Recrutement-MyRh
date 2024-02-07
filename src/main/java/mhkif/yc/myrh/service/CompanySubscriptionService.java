package mhkif.yc.myrh.service;

import mhkif.yc.myrh.dto.CompanySubscribeResponse;
import mhkif.yc.myrh.enums.SubscriptionStatus;

public interface CompanySubscriptionService {

    SubscriptionStatus getSubscriptionStatus(String companyId);
    boolean subscribe(String companyId, SubscriptionStatus subscriptionStatus , String token);
    CompanySubscribeResponse pay(String companyId, SubscriptionStatus subscriptionStatus , String token);


    boolean unsubscribe(String companyId);

}
