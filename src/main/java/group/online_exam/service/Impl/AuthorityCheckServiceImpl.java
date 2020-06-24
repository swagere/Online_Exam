package group.online_exam.service.Impl;

import group.online_exam.config.exception.CustomException;
import group.online_exam.config.exception.CustomExceptionType;
import group.online_exam.service.AuthorityCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class AuthorityCheckServiceImpl implements AuthorityCheckService {

    @Override
    public void checkStudentAuthority(Object user) {
        Map userInfo = (Map)user;
        int authority = Integer.parseInt(userInfo.get("authority").toString());
        if (authority != 0) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "该用户无权限");
        }
    }

    @Override
    public void checkTeacherAuthority(Object user) {
        Map userInfo = (Map)user;
        int authority = Integer.parseInt(userInfo.get("authority").toString());
        if (authority != 1) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "该用户无权限");
        }
    }

    @Override
    public void checkLoginStatus(Object user) {
        Map userInfo = (Map)user;
        int authority = Integer.parseInt(userInfo.get("authority").toString());
        if (authority != 1 && authority != 0) {
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "该用户无权限");
        }
    }
}
