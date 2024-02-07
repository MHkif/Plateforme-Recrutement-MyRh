package mhkif.yc.myrh.service.impl;

import mhkif.yc.myrh.dto.StripeChargeDto;
import mhkif.yc.myrh.dto.StripeTokenDto;
import mhkif.yc.myrh.model.StripeHistory;
import mhkif.yc.myrh.service.FakeStripeChargeService;
import mhkif.yc.myrh.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class StripePaymentServiceImpl implements PaymentService {


    @Value("${stripe.api.key}")
    private String secretKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = secretKey;
    }
    private final FakeStripeChargeService fakeStripeChargeService;


    public StripePaymentServiceImpl(FakeStripeChargeService fakeStripeChargeService) {

        this.fakeStripeChargeService = fakeStripeChargeService;
    }

    @Override
    public boolean isCompanyPaymentValid(String companyId) {
        return false;
    }

    @Override
    public boolean pay(String token, double amount) throws StripeException {
        init();

        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("currency", "MAD");
        params.put("source", token);

//        Charge charge = Charge.create(params);
        StripeHistory stripeHistory = this.fakeStripeChargeService.pay(params);

        return true;
    }

    @Override
    public StripeChargeDto charge(StripeChargeDto chargeRequest) {
        try{
            chargeRequest. setSuccess(false);
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount" ,(int) (chargeRequest.getAmount() * 100));
            chargeParams.put("currency", "MAD");
            chargeParams.put("description", " Payment for id " + chargeRequest.getAdditionalInfo().getOrDefault("ID_TAG",""));
            chargeParams.put("source", chargeRequest.getStripeToken());
            Map<String, Object> metaData  = new HashMap<>();
            metaData.put("id", chargeRequest.getChargeId());
            metaData.putAll(chargeRequest.getAdditionalInfo());
            chargeParams.put("metadata", metaData);
            Charge charge = Charge.create(chargeParams);
            chargeRequest.setMessage(charge.getOutcome().getSellerMessage());
            if (charge.getPaid()){
                chargeRequest.setChargeId(charge.getId());
                chargeRequest.setSuccess(true);
            }
            return chargeRequest;
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public StripeTokenDto createCardToken(StripeTokenDto model) {
        try {
            Map<String, Object> card = new HashMap<>();
            card.put("number", model.getCardNumber());
            card.put("exp_month", model.getExpMonth());
            card.put("exp_year", model.getExpYear());
            card.put("cvc", model.getCvc());
            Map<String, Object> params = new HashMap<>();
            params.put("card", card);
            Token token = Token.create(params);
            if (token != null) {
                 model.setToken(token.getId());
                 model.setSuccess(true);
            }
            return model;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }





    @Override
    public boolean cancel(String companyId) {
        return false;
    }
}


