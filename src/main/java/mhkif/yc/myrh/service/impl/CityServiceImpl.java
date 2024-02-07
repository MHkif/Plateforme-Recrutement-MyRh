package mhkif.yc.myrh.service.impl;

import mhkif.yc.myrh.dto.requests.CityReq;
import mhkif.yc.myrh.dto.responses.CityRes;
import mhkif.yc.myrh.mapper.CityMapper;
import mhkif.yc.myrh.model.City;
import mhkif.yc.myrh.repository.CityRepo;
import mhkif.yc.myrh.service.ICityService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements ICityService {

    private final CityRepo repository;
    private final CityMapper mapper;

    public CityServiceImpl(CityRepo repository, CityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CityRes getById(Integer id) {
        return null;
    }

    @Override
    public Page<CityRes> getAll(int page, int size) {
        return null;
    }

    @Override
    public CityRes create(CityReq request) {
        City city = repository.save(mapper.reqToEntity(request));
        return mapper.toRes(city);
    }

    @Override
    public CityRes update(Integer id, CityRes res) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
