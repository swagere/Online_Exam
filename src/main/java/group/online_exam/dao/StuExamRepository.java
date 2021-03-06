package group.online_exam.dao;

import group.online_exam.model.StuExam;
import io.lettuce.core.dynamic.annotation.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface  StuExamRepository extends JpaRepository<StuExam, String> {
    @Query("select u from StuExam u where u.exam_id = ?1 and u.stu_id = ?2")
    ArrayList<StuExam> getByExam_idAndStu_id(long exam_id, String stu_id);

    @Query("select distinct u.exam_id from StuExam u where u.stu_id = ?1 and u.status = ?2")
    List<Long> findExam_idByStu_IdAAndStatus(String Stu_id, StuExam.Status status); //distinct 去重

    @Query("select u.stu_id from StuExam u where u.question_id = ?1 and u.exam_id = ?2")
    ArrayList<String> getStu_idByQuestion_idAndExam_id(Long question_id, Long exam_id);

    @Query("select u.answer from StuExam u where u.question_id = ?1 and u.exam_id = ?2 and u.stu_id = ?3")
    String getAnswerById(Long question_id, Long exam_id, String stu_id);

    @Modifying
    @Transactional
    @Query("update StuExam u set u.score = :score where u.question_id = :question_id and u.exam_id = :exam_id and u.stu_id = :stu_id")
    void saveScore(@Param("score") int score, @Param("question_id") Long question_id, @Param("exam_id") Long exam_id, @Param("stu_id") String stu_id);

    @Query("select u.stu_id from StuExam u where u.exam_id = ?1")
    HashSet getStuIdByExam_id(long exam_id);

    @Query("select u from StuExam u where u.exam_id = ?1 and u.stu_id = ?2 and u.question_id = ?3")
    StuExam getByExam_idAndStu_idAndQuestion_id(long exam_id, String stu_id, long question_id);

    @Query("select distinct u.stu_id from StuExam u where u.exam_id = ?1 and u.status = ?2")
    List<String> findStu_idByStu_IdAAndStatus(Long exam_id, StuExam.Status status); //distinct 去重

    @Query("select u.status from StuExam u where u.exam_id = ?1 and u.stu_id = ?2")
    List<StuExam.Status> getStatusByExam_idAndAndStu_id(Long exam_id, String stu_id);

    @Query("select u from StuExam u where u.exam_id = ?1")
    ArrayList<StuExam> getByExam_id(long exam_id);

    @Query("select u.question_id from StuExam u where u.exam_id = ?1 and u.stu_id = ?2")
    ArrayList<Long> getQuestion_idList(long exam_id, String stu_id);

    @Modifying
    @Transactional
    @Query("update StuExam u set u.status = :status where u.question_id = :question_id and u.exam_id = :exam_id and u.stu_id = :stu_id")
    void saveStatus(@Param("status") StuExam.Status status, @Param("question_id") Long question_id, @Param("exam_id") Long exam_id, @Param("stu_id") String stu_id);

    @Modifying
    @Transactional
    @Query("update StuExam u set u.answer = :answer where u.question_id = :question_id and u.exam_id = :exam_id and u.stu_id = :stu_id")
    void saveAnswer(@Param("answer") String answer, @Param("question_id") Long question_id, @Param("exam_id") Long exam_id, @Param("stu_id") String stu_id);

    @Query("select u.score from StuExam u where u.question_id = ?1 and u.exam_id = ?2 and u.stu_id = ?3")
    Integer getScoreById(Long question_id, Long exam_id, String stu_id);

    @Transactional
    @Modifying
    @Query("delete from StuExam u where u.exam_id = :exam_id")
    void deleteByExam_id(@Param("exam_id") Long exam_id);
}
