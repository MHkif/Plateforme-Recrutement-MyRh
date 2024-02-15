package mhkif.yc.myrh.repository;

import mhkif.yc.myrh.model.ActivityArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;

public interface ActivityAreaRepo extends JpaRepository<ActivityArea, Integer> {

}
