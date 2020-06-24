package group.online_exam.controller;

import com.alibaba.fastjson.JSON;
import group.online_exam.config.exception.AjaxResponse;
import group.online_exam.model.CourseVO;
import group.online_exam.service.AuthorityCheckService;
import group.online_exam.service.HomePageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RestController
@Slf4j
@RequestMapping("/homePage")
public class HomePageController {

    @Autowired
    HomePageService homePageService;
    @Autowired
    AuthorityCheckService authorityCheckService;

    /*学生首页*/
    //学号登录获取信息
    @RequestMapping("/stu/id")
    public @ResponseBody
    AjaxResponse findStuById(@RequestBody String str, HttpServletRequest httpServletRequest) {
        authorityCheckService.checkStudentAuthority(httpServletRequest.getSession().getAttribute("userInfo"));

        //Long exam_id, String stu_id
        String stu_id = JSON.parseObject(str).get("stu_id").toString();
        Integer status = Integer.valueOf(JSON.parseObject(str).get("status").toString());
        List<Object> json = homePageService.findStuById(stu_id,status);
        return AjaxResponse.success(json);

    }


    /*教师首页*/
    //工号号登录获取信息
    @RequestMapping("/tea/id")
    public @ResponseBody
    AjaxResponse findTeaById(@RequestBody String str, HttpServletRequest httpServletRequest) {
        authorityCheckService.checkTeacherAuthority(httpServletRequest.getSession().getAttribute("userInfo"));
        String tea_id = JSON.parseObject(str).get("tea_id").toString();
        List<CourseVO> json = homePageService.findTeaById(tea_id);
        return AjaxResponse.success(json);

    }

}