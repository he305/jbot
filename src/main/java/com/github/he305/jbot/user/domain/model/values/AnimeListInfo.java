package com.github.he305.jbot.user.domain.model.values;

import com.github.he305.jbot.common.validators.StringValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class AnimeListInfo {
    private String nickname;
    private String password;

    public AnimeListInfo(String nickname, String password) {
        this.nickname = StringValidator.validate(nickname);
        this.password = StringValidator.validate(password);
    }
}
