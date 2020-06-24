package group.online_exam.dao;

import group.online_exam.model.MajorInstitude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository

public interface MajorInstitudeRepository extends JpaRepository<MajorInstitude,String> {

    @Query("select u.major_id from MajorInstitude u where u.class_id = ?1 and u.grade = ?2 and u.institude_id = ?3")
    String findMajorIdByClass_idAndGradeAndInstitude_id(String class_id, int grade, String institude_id);

}