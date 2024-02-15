package mhkif.yc.myrh.repository;

import mhkif.yc.myrh.model.Answer;
import mhkif.yc.myrh.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepo extends JpaRepository<Answer, Integer> {

    List<Answer> findAllByQuestion(Question question);
}
