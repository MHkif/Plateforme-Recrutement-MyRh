package mhkif.yc.myrh.service;

import mhkif.yc.myrh.dto.requests.CityReq;
import mhkif.yc.myrh.dto.requests.QuestionReq;
import mhkif.yc.myrh.dto.responses.CityRes;
import mhkif.yc.myrh.dto.responses.QuestionRes;
import mhkif.yc.myrh.model.City;
import mhkif.yc.myrh.model.Question;

import java.util.List;

public interface IQuestionService extends IService<Question, Integer, QuestionReq, QuestionRes> {

    List<QuestionRes> getQuestionsByProfile(int profileId);
}


