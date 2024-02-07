package mhkif.yc.myrh.mapper;

import mhkif.yc.myrh.dto.requests.RecruiterReq;
import mhkif.yc.myrh.dto.responses.RecruiterRes;
import mhkif.yc.myrh.model.Recruiter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecruiterMapper implements IMapper<Recruiter, RecruiterReq, RecruiterRes>{
    private final ModelMapper modelMapper;

    @Autowired
    public RecruiterMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RecruiterRes toRes(Recruiter recruiter) {
        return this.modelMapper.map(recruiter, RecruiterRes.class);
    }

    @Override
    public RecruiterReq toReq(Recruiter recruiter) {
        return this.modelMapper.map(recruiter, RecruiterReq.class);
    }

    @Override
    public Recruiter resToEntity(RecruiterRes recruiterRes) {
        return this.modelMapper.map(recruiterRes, Recruiter.class);
    }

    @Override
    public Recruiter reqToEntity(RecruiterReq req) {
        return this.modelMapper.map(req, Recruiter.class);
    }
}
