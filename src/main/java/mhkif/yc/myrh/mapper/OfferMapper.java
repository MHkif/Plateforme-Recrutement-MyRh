package mhkif.yc.myrh.mapper;

import mhkif.yc.myrh.dto.requests.OfferReq;
import mhkif.yc.myrh.dto.responses.OfferRes;
import mhkif.yc.myrh.model.Offer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfferMapper implements IMapper<Offer, OfferReq, OfferRes>{

    private final ModelMapper modelMapper;

    @Autowired
    public OfferMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public OfferRes toRes(Offer offer) {
        return modelMapper.map(offer, OfferRes.class);
    }

    @Override
    public OfferReq toReq(Offer offer) {
        return modelMapper.map(offer, OfferReq.class);
    }

    @Override
    public Offer resToEntity(OfferRes offerRes) {
        return modelMapper.map(offerRes, Offer.class);
    }

    @Override
    public Offer reqToEntity(OfferReq req) {
        return modelMapper.map(req, Offer.class);
    }
}
