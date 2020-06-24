package group.online_exam.dao;


import group.online_exam.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
    @Query("select u from TestCase u where u.question_id = ?1")
    TestCase getOneByQuestion_id(Long question_id);

}
