package group.online_exam.controller;

import group.online_exam.config.exception.AjaxResponse;
import group.online_exam.config.exception.CustomException;
import group.online_exam.config.exception.CustomExceptionType;
import group.online_exam.dao.StudentRepository;
import group.online_exam.dao.TeacherRepository;
import group.online_exam.model.Student;
import group.online_exam.model.Teacher;
import group.online_exam.service.AuthorityCheckService;
import group.online_exam.service.PersonalDataService;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.NonUniqueResultException;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/lostPassword")
public class LostPasswordController {
    @Autowired
    AuthorityCheckService authorityCheckService;
    @Autowired
    PersonalDataService personalDataService;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;

    /**
     * 发送邮箱验证码
     * @param params
     * @return
     * @throws Exception
     */
    @PostMapping("/sendEmail")
    public @ResponseBody
    AjaxResponse sendStudentEmail(@RequestBody Map<String, Object> params) {
        String email = (String) params.get("email");
        //验证是否被注册过
        Teacher tea = teacherRepository.findTeacherByEmail(email);
        try {
            Student stu = studentRepository.findStudentByEmail(email);
            if (tea == null && stu == null) {
                throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "该邮箱未被注册");
            }

            personalDataService.checkStudentEmail(email);
            log.info("发送邮件成功");
            return AjaxResponse.success();
        }catch (NonUniqueResultException e) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"该邮箱已存在");
        } catch (Exception e) {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR,"系统未知异常");
        }
    }

    /**
     * 检测邮箱是否经过验证
     * @param params
     * @return
     */
//    @PostMapping("/checkCode")
//    public @ResponseBody AjaxResponse checkCode(@RequestBody Map<String, Object> params){
//        personalDataService.checkCode(params);
//        return AjaxResponse.success();
//    }

    /**
     * 检测邮箱是否通过验证
     * 重置密码
     */
    @PostMapping("resetPassword")
    public @ResponseBody AjaxResponse resetPassword(@RequestBody Map<String, Object> params){
        //检测邮箱是否通过验证
        personalDataService.checkCode(params);

        //重置密码
        String email = (String) params.get("email");

        Teacher tea = teacherRepository.findTeacherByEmail(email);
        try {
            Student stu = studentRepository.findStudentByEmail(email);
            if (tea == null && stu == null) {
                throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "该邮箱未被注册");
            } else if (tea == null && stu != null) {
                //学生重置密码
                String newPassword = (String) params.get("password");

                if (newPassword.length() < 8) {
                    log.info("密码位数过少");
                    throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "密码位数过少");
                }
                System.out.println(BCrypt.checkpw(newPassword, stu.getPassword()));
                if (BCrypt.checkpw(newPassword, stu.getPassword())) {
                    throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "和原始密码重复");
                }

                try {
                    stu.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
                    log.info("密码加密成功");
                } catch (Exception e) {
                    log.info("密码加密失败");
                    throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "密码加密失败");
                }
                studentRepository.save(stu);
            } else if (tea != null && stu == null) {
                String newPassword = (String) params.get("password");
                //邮箱验证：验证码和邮箱是否一致；
                if (newPassword.length() < 8) {
                    log.info("密码位数过少");
                    throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "密码位数过少");
                }
                if (BCrypt.checkpw(newPassword, tea.getPassword())) {
                    throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "和原始密码重复");
                }

                try {
                    tea.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
                    log.info("密码加密成功");
                } catch (Exception e) {
                    log.info("密码加密失败");
                    throw new CustomException(CustomExceptionType.SYSTEM_ERROR, "密码加密失败");
                }

                teacherRepository.save(tea);
            }
            return AjaxResponse.success();
        }catch (NonUniqueResultException e) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"该邮箱已存在");
        }catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR,"系统未知异常");
        }
    }
}
