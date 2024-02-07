package mhkif.yc.myrh.mapper;

public interface IMapper<Entity, Req, Res>{
    Res toRes(Entity entity);
    Req toReq(Entity entity);
    Entity resToEntity(Res res);
    Entity reqToEntity(Req req);

}
