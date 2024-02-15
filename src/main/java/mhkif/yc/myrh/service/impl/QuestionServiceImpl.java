package mhkif.yc.myrh.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import mhkif.yc.myrh.dto.requests.QuestionReq;
import mhkif.yc.myrh.dto.responses.QuestionRes;
import mhkif.yc.myrh.exception.NotFoundException;
import mhkif.yc.myrh.mapper.QuestionMapper;
import mhkif.yc.myrh.model.Profile;
import mhkif.yc.myrh.model.Question;
import mhkif.yc.myrh.repository.ProfileRepo;
import mhkif.yc.myrh.repository.QuestionRepo;
import mhkif.yc.myrh.service.IQuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements IQuestionService {

    private final QuestionRepo questionRepo;
    private final ProfileRepo profileRepo;
    private final QuestionMapper questionMapper;

    @Override
    public QuestionRes getById(Integer id) {
        Question question = questionRepo.findById(id).orElseThrow(() -> new NotFoundException("Question not found for the given id : "+ id));
        return questionMapper.toRes(question);
    }

    @Override
    public Page<QuestionRes> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return questionRepo.findAll(pageRequest).map(questionMapper::toRes);
    }

    @Override
    public List<QuestionRes> getQuestionsByProfile(int profileId){
        Profile profile = profileRepo.findById(profileId).orElseThrow(() -> new EntityNotFoundException("Profile not found"));

        return  questionRepo.findAllByProfile(profile).stream().map(questionMapper::toRes).toList();
    }

    @Override
    public QuestionRes create(QuestionReq request) {
        return null;
    }

    @Override
    public QuestionRes update(Integer id, QuestionRes request) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
