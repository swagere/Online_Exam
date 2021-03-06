package group.online_exam.service.Impl;

import group.online_exam.dao.*;
import group.online_exam.model.Course;
import group.online_exam.model.CourseVO;
import group.online_exam.model.Exam;
import group.online_exam.model.StuExam;
import group.online_exam.service.ExamService;
import group.online_exam.service.HomePageService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class HomePageServiceimpl implements HomePageService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StuExamRepository stuExamRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teaRepository;
    @Autowired
    private TeaCoRepository teaCoRepository;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    ExamService examService;

    @Override
    public List<Object> findStuById(String stu_id, int status) {

        List<Exam> exams = new ArrayList<>();
        List<Object> ret = new ArrayList<>();

            //正在进行中 学生未完成的考试
        if(status == 0){
            StuExam.Status stuExam_status = StuExam.Status.WILL;
            Exam.ProgressStatus exam_status = Exam.ProgressStatus.ING;
            List<Long> exam_idList = stuExamRepository.findExam_idByStu_IdAAndStatus(stu_id, stuExam_status);
            if (exam_idList.isEmpty()) {
                return null;
            }
            exams = examRepository.findExamsByExam_idAAndProgress_status(exam_idList,exam_status);

            //正在进行中 学生已完成的考试
        }else if(status == 1){
            StuExam.Status stuExam_status = StuExam.Status.DONE;
            Exam.ProgressStatus exam_status = Exam.ProgressStatus.ING;
            List<Long> exam_idList = stuExamRepository.findExam_idByStu_IdAAndStatus(stu_id, stuExam_status);
            if (exam_idList.isEmpty()) {
                return null;
            }
            exams = examRepository.findExamsByExam_idAAndProgress_status(exam_idList,exam_status);

            //已阅卷的考试
        }else if(status == 2){
            StuExam.Status stuExam_status = StuExam.Status.DONE;
            Exam.ProgressStatus exam_status = Exam.ProgressStatus.DONE;
            List<Long> exam_idList = stuExamRepository.findExam_idByStu_IdAAndStatus(stu_id, stuExam_status);
            if (exam_idList.isEmpty()) {
                return null;
            }
            exams = examRepository.findExamsByExam_idAAndProgress_status(exam_idList,exam_status);

            //未阅卷的考试
        }else if (status == 3){
            StuExam.Status stuExam_status = StuExam.Status.WILL;
            Exam.ProgressStatus exam_status = Exam.ProgressStatus.DONE;
            List<Long> exam_idList = stuExamRepository.findExam_idByStu_IdAAndStatus(stu_id, stuExam_status);
            if (exam_idList.isEmpty()) {
                return null;
            }
            exams = examRepository.findExamsByExam_idAAndProgress_status(exam_idList,exam_status);
        }
        for (Exam exam : exams) {
            Map<String, Object> item = new HashMap<>();
            String co_name = courseRepository.getNameByCo_id(exam.getCo_id());
            String tea_name = teaRepository.getNameByTea_id(exam.getTea_id());
            item.put("exam", exam);
            item.put("co_name", co_name);
            item.put("tea_name", tea_name);
            item.put("grade",examService.getStuExamScore(exam.getExam_id(), stu_id));
            ret.add(item);
        }
        return ret;

    }

    @Override
    public List<Object> findTeaById(String tea_id, int status) {
        List<Exam> exams = new ArrayList<>();
        List<Object> ret = new ArrayList<>();

        //未开始的考试
        if (status == 0) {
            Exam.ProgressStatus progressStatus = Exam.ProgressStatus.WILL;
            exams = examRepository.findExamsByTea_idAndProgress_status(tea_id, progressStatus);
        }

        //正在进行的考试
        else if (status == 1) {
            Exam.ProgressStatus progressStatus = Exam.ProgressStatus.ING;
            exams = examRepository.findExamsByTea_idAndProgress_status(tea_id, progressStatus);
        }

        //已结束的考试
        else if (status == 2) {
            Exam.ProgressStatus progressStatus = Exam.ProgressStatus.DONE;
            exams = examRepository.findExamsByTea_idAndProgress_status(tea_id, progressStatus);
        }

        for (Exam exam : exams) {
            Map<String, Object> item = new HashMap<>();
            String co_name = courseRepository.getNameByCo_id(exam.getCo_id());
            String tea_name = teaRepository.getNameByTea_id(exam.getTea_id());
            item.put("exam", exam);
            item.put("co_name", co_name);
            item.put("tea_name", tea_name);
            ret.add(item);
        }
        return ret;
    }



    @Override
    public List<CourseVO> findCourseById(String tea_id) {
        //获取教师所授课程
        List<String> co_idList = teaCoRepository.findCo_idByTea_Id(tea_id);
        if (co_idList.isEmpty()) {
            return null;
        }
        List<Course> course = courseRepository.findCourseByCo_idIn(co_idList);
        List<CourseVO> courses = new ArrayList<>();
        for (int i = 0; i < course.size(); i++) {
            courses.add(dozerBeanMapper.map(course.get(i),CourseVO.class));
            courses.get(i).setMajor_list(String2List(course.get(i).getMajor()));
        }
        return courses;
    }

    @Override
    public String List2String(ArrayList<String> list){
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<list.size();++i){
            try{
                jsonArray.put(list.get(i));
            }catch (Exception e){
                //这里处理异常
                break;
            }
        }
        return jsonArray.toString();
    }

    @Override
    public ArrayList<String> String2List(String s){
        ArrayList<String> list = new ArrayList<>();
        try{
            JSONArray jsonArray = new JSONArray(s);
            for(int i=0;i<jsonArray.length();++i){
                list.add(jsonArray.getString(i));
            }
        }catch (Exception e){
            //这里处理异常
        }
        return list;
    }
}
