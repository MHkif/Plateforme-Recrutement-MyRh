package mhkif.yc.myrh.repository;

import mhkif.yc.myrh.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question, Integer> {
}
