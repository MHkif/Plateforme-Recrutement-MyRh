package mhkif.yc.myrh.service;

import mhkif.yc.myrh.dto.requests.JobSeekerReq;
import mhkif.yc.myrh.dto.responses.JobSeekerRes;
import mhkif.yc.myrh.model.JobSeeker;

public interface IJobSeekerService extends IService<JobSeeker, Integer, JobSeekerReq, JobSeekerRes>{
    JobSeekerRes auth(String email, String password);
}
