package mhkif.yc.myrh.service;

import mhkif.yc.myrh.dto.requests.CompanyJobApplicantReq;
import mhkif.yc.myrh.dto.requests.JobApplicantReq;
import mhkif.yc.myrh.dto.responses.JobApplicantRes;
import mhkif.yc.myrh.model.JobApplicant;
import mhkif.yc.myrh.model.JobApplicantId;

import java.util.List;

public interface IJobApplicantService extends IService<JobApplicant, JobApplicantId, JobApplicantReq, JobApplicantRes>{
    JobApplicantRes updateStatus(CompanyJobApplicantReq req);
    List<JobApplicantRes> getAllByCompany(int companyId );
}
