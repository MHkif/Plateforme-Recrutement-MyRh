package mhkif.yc.myrh.service.impl;

import mhkif.yc.myrh.dto.requests.ActivityAreaReq;
import mhkif.yc.myrh.dto.responses.ActivityAreaRes;
import mhkif.yc.myrh.mapper.ActivityAreaMapper;
import mhkif.yc.myrh.model.ActivityArea;
import mhkif.yc.myrh.repository.ActivityAreaRepo;
import mhkif.yc.myrh.service.IActivityAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ActivityAreaServiceImpl implements IActivityAreaService {

    private final ActivityAreaRepo repository;
    private final ActivityAreaMapper mapper;

    @Autowired
    public ActivityAreaServiceImpl(ActivityAreaRepo repository, ActivityAreaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ActivityAreaRes getById(Integer id) {
        return null;
    }

    @Override
    public  Page<ActivityAreaRes> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).map(mapper::toRes);
    }

    @Override
    public ActivityAreaRes create(ActivityAreaReq request) {
        ActivityArea profile = repository.save(mapper.reqToEntity(request));
        return mapper.toRes(profile);
    }

    @Override
    public ActivityAreaRes update(Integer id, ActivityAreaRes res) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
