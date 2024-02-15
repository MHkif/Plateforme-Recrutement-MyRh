package mhkif.yc.myrh.repository;

import mhkif.yc.myrh.model.Profile;
import mhkif.yc.myrh.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface QuestionRepo extends JpaRepository<Question, Integer> {
    List<Question> findAllByProfile(Profile profile);
}
