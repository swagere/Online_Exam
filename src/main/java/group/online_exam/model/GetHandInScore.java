package group.online_exam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetHandInScore {
    public String stu_id;
    public Long exam_id;
    List<StuExam> scoreList;
}
