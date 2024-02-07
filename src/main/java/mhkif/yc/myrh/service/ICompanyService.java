package mhkif.yc.myrh.service;

import mhkif.yc.myrh.dto.requests.CompanyReq;
import mhkif.yc.myrh.dto.responses.CompanyRes;
import mhkif.yc.myrh.model.Company;

public interface ICompanyService extends IService<Company, Integer, CompanyReq, CompanyRes>{
    CompanyRes auth(String email, String password);
    Boolean verifyToken(String token) throws Exception;

    Boolean sendVerification(String id) throws Exception;
}
