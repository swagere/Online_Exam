package group.online_exam.service;

import group.online_exam.model.Student;
import group.online_exam.model.Teacher;

public interface RegisterService {
    void saveStudent(Student student) throws Exception;

    void saveTeacher(Teacher teacher) throws Exception;

    void checkTeacherRepeat(String email);

    void sendTeacherEmail(String receiver) throws Exception;

    void checkStudentRepeat(String email);

    void sendStudentEmail(String receiver) throws Exception;
}
