package group.online_exam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@IdClass(StuCoPK.class)
@Table(name = "stu_co")
public class StuCo implements Serializable{
    @Id
    @Column(length = 32,nullable = false)
    private String co_id;

    @Id
    @Column(length = 32,nullable = false)
    private String tea_id;

    @Id
    @Column(length = 32,nullable = false)
    private String stu_id;
}
