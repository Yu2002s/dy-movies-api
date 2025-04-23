package xyz.jdynb.dymovies.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginFromVo {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String code;
}
