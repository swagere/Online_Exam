package group.online_exam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetQuestion implements Serializable {

    public static enum Type {
        Single,
        MultipleChoice,
        Judge,
        FillInTheBlank,
        Discussion,
        Program,
        Normal_Program,
        SpecialJudge_Program;
    }

    private Long question_id;
    @NotBlank(message = "教师id不为空")
    private  String tea_id;
    private String question;
    private String tag;
    private  String options;
    private String answer;

    @Enumerated(EnumType.STRING)
    private Type type = null;

    private ArrayList<ToTestCase> test_case;

    private String tip; //提示
}
