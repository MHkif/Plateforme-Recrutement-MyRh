package mhkif.yc.myrh.mapper;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import mhkif.yc.myrh.dto.requests.ProfileReq;
import mhkif.yc.myrh.dto.responses.ProfileRes;
import mhkif.yc.myrh.model.Profile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileMapper implements IMapper<Profile, ProfileReq, ProfileRes>{


    private final ModelMapper mapper;
    @Override
    public ProfileRes toRes(Profile profile) {
        return mapper.map(profile,ProfileRes.class);
    }

    @Override
    public ProfileReq toReq(Profile profile) {
        return mapper.map(profile,ProfileReq.class);
    }

    @Override
    public Profile resToEntity(ProfileRes profileRes) {
        return mapper.map(profileRes,Profile.class);
    }

    @Override
    public Profile reqToEntity(ProfileReq profileReq) {
        return mapper.map(profileReq,Profile.class);
    }
}
