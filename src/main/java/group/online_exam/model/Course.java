package group.online_exam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "course")
public class Course implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String co_id;
    @Column
    private String name;
    @Column
    private String credit;//学分
    @Column
    private String school_hour;//学时
    @Column
    private String exam_score;
    @Column
    private String common_socre;
    @Column
    private String exam_proportion;//卷面比例
    @Column
    private Timestamp end_time;
    @Column
    private Timestamp begin_time;

    @Column
    private String major;

}
