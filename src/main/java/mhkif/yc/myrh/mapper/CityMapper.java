package mhkif.yc.myrh.mapper;

import mhkif.yc.myrh.dto.requests.CityReq;
import mhkif.yc.myrh.dto.responses.CityRes;
import mhkif.yc.myrh.model.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMapper implements IMapper<City, CityReq, CityRes>{

    private final ModelMapper modelMapper;

    @Autowired
    public CityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CityRes toRes(City city) {
        return modelMapper.map(city, CityRes.class);
    }

    @Override
    public CityReq toReq(City city) {
        return modelMapper.map(city, CityReq.class);
    }

    @Override
    public City resToEntity(CityRes cityRes) {
        return modelMapper.map(cityRes, City.class);
    }

    @Override
    public City reqToEntity(CityReq cityReq) {
        return modelMapper.map(cityReq, City.class);
    }
}
