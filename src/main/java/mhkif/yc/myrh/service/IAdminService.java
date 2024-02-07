package mhkif.yc.myrh.service;

import mhkif.yc.myrh.dto.requests.AdminReq;
import mhkif.yc.myrh.dto.responses.AdminRes;
import mhkif.yc.myrh.model.Admin;

public interface IAdminService extends IService<Admin, Integer, AdminReq, AdminRes>{
    AdminRes auth(String email, String password);
}
