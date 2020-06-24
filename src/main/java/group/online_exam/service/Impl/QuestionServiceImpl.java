package group.online_exam.service.Impl;

import group.online_exam.dao.QuestionRepository;
import group.online_exam.model.GetQuestion;
import group.online_exam.model.Question;
import group.online_exam.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@EnableAsync
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Resource
    private Mapper dozerMapper;

    @Override
    public long saveQuestion(GetQuestion getQuestion) throws Exception {
        //将getQuestion类转化为question类
        Question question = dozerMapper.map(getQuestion, Question.class);
        questionRepository.save(question);
        return question.getQuestion_id();
    }

}
