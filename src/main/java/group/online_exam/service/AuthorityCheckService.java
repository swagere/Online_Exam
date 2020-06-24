package group.online_exam.service;

public interface AuthorityCheckService {
    void checkStudentAuthority(Object user);
    void checkTeacherAuthority(Object user);
    void checkLoginStatus(Object user);
}
