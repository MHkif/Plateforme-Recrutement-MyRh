package mhkif.yc.myrh.service;

import mhkif.yc.myrh.dto.responses.JobSeekerRes;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IJobSeekerFilterService   {
    Page<JobSeekerRes> filterAll(Map<String,String> params);
}
