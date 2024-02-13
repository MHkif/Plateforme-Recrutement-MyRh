package mhkif.yc.myrh.service;

import mhkif.yc.myrh.dto.requests.ProfileReq;
import mhkif.yc.myrh.dto.responses.ProfileRes;
import mhkif.yc.myrh.model.Profile;

import java.util.Set;

public interface IProfileService extends IService<Profile,Integer, ProfileReq, ProfileRes>{

     Set<ProfileRes> getAllByArea(int id);
}
