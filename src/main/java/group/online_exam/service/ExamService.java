package group.online_exam.service;


import group.online_exam.model.Exam;
import group.online_exam.model.ExamQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ExamService {
    long saveToExam(Exam exam) throws Exception;
    void saveQuestionToExam(ExamQuestion examQuestion) throws Exception;
    void distributeExamToStudent(long exma_id, String co_id, String tea_id) throws Exception;
    void judgeGeneralQuestion(long exam_id);
    void saveToStuExam(String data, Long exam_id, String stu_id);
    Map getDiscussion(Long exam_id);
    Map getExamInfo(Long exam_id, int option);
    List<Long> getExam(String tea_id, String co_id);
    List getStuQuesInfo(Long exam_id, String stu_id);
    int getStuExamScore(Long exam_id, String stu_id);
    String getExamName(Long exam_id);
    Map getWholeExam(Long exam_id);
    ArrayList getStuExam(Long exam_id);
}
