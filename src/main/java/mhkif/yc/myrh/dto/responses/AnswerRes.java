package mhkif.yc.myrh.dto.responses;

import lombok.Data;

@Data
public class AnswerRes {

    private int id;
   // private QuestionRes question;
    private String answer;
    private boolean rightAnswer;
}
