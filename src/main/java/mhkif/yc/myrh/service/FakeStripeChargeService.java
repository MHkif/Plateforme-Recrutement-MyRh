package mhkif.yc.myrh.service;

import mhkif.yc.myrh.model.StripeHistory;

import java.util.Map;

public interface FakeStripeChargeService {
    public StripeHistory pay(Map<String, Object> params);
}
