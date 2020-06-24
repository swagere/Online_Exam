package group.online_exam.controller;



import group.online_exam.config.exception.AjaxResponse;
import group.online_exam.config.exception.CustomException;
import group.online_exam.config.exception.CustomExceptionType;
import group.online_exam.service.AuthorityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/AuthorityChange")
public class AuthorityController {

    @Resource
    AuthorityService authorityService;


    @GetMapping("/{page}")
    public @ResponseBody
    AjaxResponse TeaView(@PathVariable Integer page, HttpServletRequest request){

        Map m = (Map) request.getSession().getAttribute("userInfo");
        int authority  = (int) m.get("authority");
        //
        System.out.println("这里得到的限权是"+authority);
        List l = authorityService.find(page,authority);//null 或者一个 List

        if(l.isEmpty()){
           return AjaxResponse.error(new CustomException(CustomExceptionType.SYSTEM_ERROR,"页码超过限制，获取可操作用户失败"));
        }else {
           return  AjaxResponse.success(l);
        }
    }

}
