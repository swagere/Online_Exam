package group.online_exam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Login implements Serializable {
    @NotBlank(message = "学号/工号/手机号不能为空")
    private String keyword;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String id = null;

    private int authority;
}
