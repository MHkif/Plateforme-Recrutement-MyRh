package mhkif.yc.myrh.repository;

import mhkif.yc.myrh.model.Recruiter;

public interface RecruiterRepo  {
    boolean existsByEmail(String email);

    Recruiter findByEmail(String email);
}
