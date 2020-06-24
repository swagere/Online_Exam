package group.online_exam.dao;

import group.online_exam.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MajorRepository extends JpaRepository<Major, String> {
    @Query("select name from Major where major_id = ?1")
    String getNameByMajor_id(String major_id);
}