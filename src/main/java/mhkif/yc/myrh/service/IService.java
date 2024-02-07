package mhkif.yc.myrh.service;

import org.springframework.data.domain.Page;

public interface IService<Entity, pk, Req, Res>  {
    Res getById(pk id);
    Page<Res> getAll(int page, int size);
    Res create(Req request);
    Res update(pk id, Res request);
    void deleteById(pk id);

}
