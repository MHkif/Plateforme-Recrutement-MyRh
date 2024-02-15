package mhkif.yc.myrh.service;

import mhkif.yc.myrh.dto.requests.ActivityAreaReq;
import mhkif.yc.myrh.dto.responses.ActivityAreaRes;
import mhkif.yc.myrh.model.ActivityArea;
import org.springframework.stereotype.Repository;


public interface IActivityAreaService extends IService<ActivityArea, Integer, ActivityAreaReq, ActivityAreaRes>{
}
