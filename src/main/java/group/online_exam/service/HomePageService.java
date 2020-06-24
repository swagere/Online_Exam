package group.online_exam.service;



import group.online_exam.model.CourseVO;

import java.util.ArrayList;
import java.util.List;

public interface HomePageService {
    List<Object> findStuById(String stu_id, int status) ;

    List<CourseVO> findTeaById(String tea_id);

    String List2String(ArrayList<String> list);

    ArrayList<String> String2List(String s);

}
