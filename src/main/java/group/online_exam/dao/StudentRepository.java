package group.online_exam.dao;


import group.online_exam.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("select u.stu_id from Student u where u.telephone = ?1")
    String findStu_id_idByPhone(String phone);

    @Query("select u from Student u where u.stu_id = ?1")
    Student findStudentByStu_id(String stu_id);

    @Query("select u from Student u where u.email = ?1")
    Student findStudentByEmail(String email) throws Exception;

    @Query("select u from  Student u where u.telephone = ?1")
    Student findStudentByTelephone(String telephone);

    @Query("select u.name from Student u where  u.stu_id = ?1")
    String findNameByStu_id(String stu_id);
}