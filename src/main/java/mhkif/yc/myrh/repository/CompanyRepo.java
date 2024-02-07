package mhkif.yc.myrh.repository;

import mhkif.yc.myrh.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {
    boolean existsByEmail(String email);

    Optional<Company> findByEmail(String email);

}
