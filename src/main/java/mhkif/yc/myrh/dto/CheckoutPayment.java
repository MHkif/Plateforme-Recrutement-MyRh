package mhkif.yc.myrh.dto;

import mhkif.yc.myrh.enums.SubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckoutPayment {

	private String name;
	private SubscriptionStatus subscriptionStatus;
	private String currency;
	private String successUrl;
	private String cancelUrl;
	private long amount;
	private long quantity;

}