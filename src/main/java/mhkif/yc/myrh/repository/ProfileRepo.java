package mhkif.yc.myrh.repository;

import mhkif.yc.myrh.model.ActivityArea;
import mhkif.yc.myrh.model.Profile;
import org.hibernate.mapping.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Integer> {

    Set<Profile> findAllByActivityArea_Id(Integer area);

}
