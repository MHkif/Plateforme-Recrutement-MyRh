package mhkif.yc.myrh.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mhkif.yc.myrh.dto.requests.ProfileReq;
import mhkif.yc.myrh.dto.responses.ProfileRes;
import mhkif.yc.myrh.mapper.ProfileMapper;
import mhkif.yc.myrh.repository.ProfileRepo;
import mhkif.yc.myrh.service.IProfileService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements IProfileService {
    private  final ProfileMapper mapper;
    private final ProfileRepo repo;
    @Override
    public ProfileRes getById(Integer id) {
        return mapper.toRes(repo.getReferenceById(id));
    }

    @Override
    public Page<ProfileRes> getAll(int page, int size) {
        return null;
    }

    @Override
    public ProfileRes create(ProfileReq request) {
        return null;
    }

    @Override
    public ProfileRes update(Integer id, ProfileRes request) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
    }

    public Set<ProfileRes> getAllByArea(int id){
        return repo.findAllByActivityArea_Id(id).stream().map(mapper::toRes).collect(Collectors.toSet());
    }
}
