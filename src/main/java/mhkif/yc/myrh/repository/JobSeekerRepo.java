package mhkif.yc.myrh.repository;

import mhkif.yc.myrh.enums.UserStatus;
import mhkif.yc.myrh.model.JobSeeker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobSeekerRepo extends JpaRepository<JobSeeker, Integer> {
    boolean existsByEmail(String email);
    Optional<JobSeeker> findByEmail(String email);
    Page<JobSeeker> getAllByStatus(UserStatus userStatus, Pageable pageable);
}
