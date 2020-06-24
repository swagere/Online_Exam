package group.online_exam.service;

import group.online_exam.model.GetQuestion;

public interface QuestionService {
    long saveQuestion(GetQuestion getQuestion) throws Exception;
}
