package mhkif.yc.myrh.service;

import mhkif.yc.myrh.dto.requests.OfferReq;
import mhkif.yc.myrh.dto.responses.OfferRes;
import mhkif.yc.myrh.enums.StudyLevel;
import mhkif.yc.myrh.model.Offer;
import org.springframework.data.domain.Page;

public interface IOfferService extends IService<Offer, Integer, OfferReq, OfferRes>{
    Page<OfferRes> search(int page, int size, String title, String description, String domain, String city, StudyLevel level, String job);
    OfferRes updateVisibility(int offerId, String offerStatus);


    boolean verifyCompanySubscription(int companyId);
}
