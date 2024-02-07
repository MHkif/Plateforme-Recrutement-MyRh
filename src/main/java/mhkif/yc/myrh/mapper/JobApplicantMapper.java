package mhkif.yc.myrh.mapper;

import mhkif.yc.myrh.dto.requests.JobApplicantReq;
import mhkif.yc.myrh.dto.responses.JobApplicantRes;
import mhkif.yc.myrh.model.JobApplicant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobApplicantMapper implements IMapper<JobApplicant, JobApplicantReq, JobApplicantRes>{

    private final ModelMapper modelMapper;
    @Override
    public JobApplicantRes toRes(JobApplicant jobApplicant) {
        return this.modelMapper.map(jobApplicant, JobApplicantRes.class);
    }

    @Override
    public JobApplicantReq toReq(JobApplicant jobApplicant) {
        return this.modelMapper.map(jobApplicant, JobApplicantReq.class);
    }

    @Override
    public JobApplicant resToEntity(JobApplicantRes jobApplicantRes) {
        return this.modelMapper.map(jobApplicantRes, JobApplicant.class);
    }

    @Override
    public JobApplicant reqToEntity(JobApplicantReq jobApplicantReq) {
        return this.modelMapper.map(jobApplicantReq, JobApplicant.class);
    }
}
