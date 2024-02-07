package mhkif.yc.myrh.repository;

import mhkif.yc.myrh.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {
    boolean existsByEmail(String email);

    Admin findByEmail(String email);
}
