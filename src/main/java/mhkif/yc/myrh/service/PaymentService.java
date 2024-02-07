package mhkif.yc.myrh.service;

import mhkif.yc.myrh.dto.StripeChargeDto;
import mhkif.yc.myrh.dto.StripeTokenDto;
import com.stripe.exception.StripeException;

public interface PaymentService {

    boolean isCompanyPaymentValid(String companyId);

    //based on the subscription status, the company will be able to pay for the subscription
    boolean pay(String token , double amount)throws StripeException;
    boolean cancel(String companyId);
    StripeTokenDto createCardToken(StripeTokenDto model);
    StripeChargeDto charge(StripeChargeDto stripeChargeDto);

}
