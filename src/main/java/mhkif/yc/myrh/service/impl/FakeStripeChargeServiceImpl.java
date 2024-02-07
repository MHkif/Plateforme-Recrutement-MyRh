package mhkif.yc.myrh.service.impl;

import mhkif.yc.myrh.model.StripeHistory;
import mhkif.yc.myrh.repository.StripHistoryRepository;
import mhkif.yc.myrh.service.FakeStripeChargeService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FakeStripeChargeServiceImpl implements FakeStripeChargeService {

    private  final StripHistoryRepository stripHistoryRepository;

    public FakeStripeChargeServiceImpl(StripHistoryRepository stripHistoryRepository) {
        this.stripHistoryRepository = stripHistoryRepository;
    }
    @Override
    public StripeHistory pay(Map<String, Object> params){
        StripeHistory stripeHistory = new StripeHistory();
        stripeHistory.setAmount((double) params.get("amount"));
        stripeHistory.setCompanyId((String) params.get("source"));
        stripeHistory.setCurrency((String) params.get("currency"));
        stripeHistory.setToken((String) params.get("source"));
        stripeHistory.setCreatedAt(java.time.LocalDateTime.now());
        return stripHistoryRepository.save(stripeHistory);
    };
}