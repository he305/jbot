package com.github.he305.jbot.user.domain.model.values;

import com.github.he305.jbot.common.validators.StringValidator;
import lombok.NonNull;

public record AnimeListInfo(
        @NonNull String nickname,
        @NonNull String password
) {
    public AnimeListInfo(String nickname, String password) {
        this.nickname = StringValidator.validate(nickname);
        this.password = StringValidator.validate(password);
    }
}
