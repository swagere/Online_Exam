package group.online_exam.service;

import com.alibaba.fastjson.JSONObject;
import group.online_exam.model.GetQuestion;

import java.util.ArrayList;

public interface JudgeService {

//    JSONObject judge(String src, String language, Long testCaseId);

    void addTestCase(GetQuestion getQuestion);

    //Future<String> writeFile(Long question_id, int type, GetQuestion getQuestion) throws InterruptedException;

//    void saveProgramQustionFile(Long question_id, int type, GetQuestion getQuestion);
//    void addToDocker(String path, Long question_id, ArrayList<String> fileNames);

//    void deleteFile(Long question_id);

//    String transformToMd5(String output);

//    JudgeResult transformToResult(JSONObject json, String stu_id, String code, String language, Long question_id, Long exam_id);

//    ArrayList<String> getFileNames(Long question_id);

//    String getJudgeResult(int result);
}
