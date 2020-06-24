package group.online_exam.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class MajorInstitudePK implements Serializable {
    private String institude_id;
    private int grade;
    private String class_id;
}
