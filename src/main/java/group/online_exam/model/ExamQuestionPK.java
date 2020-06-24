package group.online_exam.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExamQuestionPK implements Serializable {
    private Long exam_id;
    private Long question_id;
}
