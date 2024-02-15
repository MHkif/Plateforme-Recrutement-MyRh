package mhkif.yc.myrh.dto.requests;

import lombok.Data;
import mhkif.yc.myrh.dto.responses.AnswerRes;
import mhkif.yc.myrh.dto.responses.ProfileRes;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionReq {

    private ProfileRes profile;
    private String question;
}
