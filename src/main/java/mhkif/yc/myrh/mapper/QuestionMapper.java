package mhkif.yc.myrh.mapper;


import lombok.RequiredArgsConstructor;

import mhkif.yc.myrh.dto.requests.QuestionReq;
import mhkif.yc.myrh.dto.responses.QuestionRes;
import mhkif.yc.myrh.model.Question;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionMapper implements IMapper<Question, QuestionReq, QuestionRes>{
    private final ModelMapper modelMapper;

    @Override
    public QuestionRes toRes(Question question){
        return this.modelMapper.map(question, QuestionRes.class);
    }

    @Override
    public QuestionReq toReq(Question question){
        return this.modelMapper.map(question, QuestionReq.class);
    }
    @Override
    public Question resToEntity(QuestionRes res){
        return this.modelMapper.map(res, Question.class);
    }
    @Override
    public Question reqToEntity(QuestionReq req) {
        return this.modelMapper.map(req, Question.class);
    }

}
