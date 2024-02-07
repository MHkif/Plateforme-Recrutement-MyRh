package mhkif.yc.myrh.controller;

import mhkif.yc.myrh.dto.CheckoutPayment;
import mhkif.yc.myrh.dto.CompanySubscribeRequest;
import mhkif.yc.myrh.dto.CompanySubscribeResponse;
import mhkif.yc.myrh.dto.HttpRes;
import mhkif.yc.myrh.service.CompanySubscriptionService;
import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("myrh/api/v1/company/subscriptions")
@CrossOrigin("*")
public class CompanySubscriptionController {

    @Value("${stripe.api.key}")
    String secretKey;
    private static Gson gson = new Gson();
    private final CompanySubscriptionService companySubscriptionService;

    public CompanySubscriptionController(CompanySubscriptionService companySubscriptionService) {
        this.companySubscriptionService = companySubscriptionService;
    }

    //:Subscription PAYMENT
    @PostMapping("/subscribe")
    public ResponseEntity<HttpRes> subscribe(@RequestBody CompanySubscribeRequest request) {
        boolean isSubscribed  = companySubscriptionService.subscribe(request.getCompanyId(), request.getSubscriptionStatus(), request.getToken());
        if (!isSubscribed){
            return ResponseEntity.badRequest().body(
                    HttpRes.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .path("myrh/api/v1/company/subscriptions/subscribe")
                            .status(HttpStatus.BAD_REQUEST)
                            .message("Subscription failed")
                            .developerMessage("Subscription failed")
                            .data(Map.of("response", "Subscription failed"))
                            .build()
            );
        }
        return ResponseEntity.ok(
                HttpRes.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .statusCode(HttpStatus.OK.value())
                        .path("myrh/api/v1/company/subscriptions/subscribe")
                        .status(HttpStatus.OK)
                        .message("Subscription successful")
                        .developerMessage("Subscription successful")
                        .data(Map.of("response", "Subscription successful"))
                        .build());
    }

    //THIS FUNCTION IS WORTHLESS
    @PostMapping("/subscribe/pay")
    public ResponseEntity<CompanySubscribeResponse> pay(@RequestBody CompanySubscribeRequest request) {
        return ResponseEntity.ok(companySubscriptionService.pay(request.getCompanyId(), request.getSubscriptionStatus(), request.getToken()));
    }


    private  void init() {
        Stripe.apiKey = this.secretKey;
    }
    @PostMapping("/payment")
    public String paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException {
        // We initilize stripe object with the api key
        init();
        // We create a  stripe session parameters
        SessionCreateParams params = SessionCreateParams.builder()
                // We will use the credit card payment method
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl())
                .setCancelUrl(
                        payment.getCancelUrl())
                .addLineItem(
                        SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount()*100)
                                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                                        .builder().setName(payment.getName()).build())
                                                .build())
                                .build())
                .build();
        // create a stripe session
        Session session = Session.create(params);
        Map<String, String> responseData = new HashMap<>();
        // We get the sessionId and we putted inside the response data you can get more info from the session object
        responseData.put("id", session.getId());
        // We can return only the sessionId as a String
        return gson.toJson(responseData);
    }

}
