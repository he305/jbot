package com.github.he305.jbot.user.domain.model.values;

import com.github.he305.jbot.common.validators.StringValidator;
import lombok.NonNull;

public record UserInfo(
        @NonNull String name,
        @NonNull AnimeListInfo animeListInfo
) {
    public UserInfo(String name, AnimeListInfo animeListInfo) {
        this.name = StringValidator.validate(name);
        this.animeListInfo = animeListInfo;
    }
}
