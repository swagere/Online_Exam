package group.online_exam.dao;


import group.online_exam.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select answer from Question  where question_id = ?1")
    String findAnswerById(long question_id);

    @Query("select type from Question where question_id = ?1")
    Question.Type findTypeByQuestion_id(Long question_id);

    @Query("select question from Question where question_id = ?1")
    String getQuestionByQuestion_id(Long question_id);

    @Query("select  answer from Question where question_id = ?1")
    String getAnswerByQuestion_id(Long question_id);

    @Query("select u from Question u where u.question_id = ?1")
    Question getOneByQuestion_id(Long question_id);

    @Query("select max(u.question_id) from Question u")
    Long getMaxQuestion_id();
}