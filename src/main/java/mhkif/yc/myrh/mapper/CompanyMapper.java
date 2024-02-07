package mhkif.yc.myrh.mapper;

import mhkif.yc.myrh.dto.requests.CompanyReq;
import mhkif.yc.myrh.dto.responses.CompanyRes;
import mhkif.yc.myrh.model.Company;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements IMapper<Company, CompanyReq, CompanyRes>{
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CompanyRes toRes(Company company){
        return this.modelMapper.map(company, CompanyRes.class);
    }

    @Override
    public CompanyReq toReq(Company company){
        return this.modelMapper.map(company, CompanyReq.class);
    }
    @Override
    public Company resToEntity(CompanyRes res){
        return this.modelMapper.map(res, Company.class);
    }
    @Override
    public Company reqToEntity(CompanyReq req) {
        return this.modelMapper.map(req, Company.class);
    }



}
