package group.online_exam.service;

import group.online_exam.model.Login;

public interface LoginService {
    //学号或者工号加密码
    Login LoginId(Login login);

    //手机号加密码
    Login loginPhone(Login login);
}
