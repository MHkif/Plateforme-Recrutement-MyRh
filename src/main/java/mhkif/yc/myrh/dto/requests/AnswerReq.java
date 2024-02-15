package mhkif.yc.myrh.dto.requests;

import lombok.Data;

@Data
public class AnswerReq {
    // private QuestionRes question;
    private String answer;
    private boolean rightAnswer;
}
