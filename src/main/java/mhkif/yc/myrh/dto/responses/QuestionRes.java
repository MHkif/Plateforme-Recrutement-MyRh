package mhkif.yc.myrh.dto.responses;

import jakarta.persistence.*;
import lombok.Data;
import mhkif.yc.myrh.enums.OfferStatus;
import mhkif.yc.myrh.enums.StudyLevel;
import mhkif.yc.myrh.model.Answer;
import mhkif.yc.myrh.model.Profile;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionRes {
    private int id;
    private ProfileRes profile;
    private List<AnswerRes> answers = new ArrayList<>();
    private String question;

}
