package com.github.he305.jbot.user.domain.model.values;

import com.github.he305.jbot.common.validators.StringValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class UserInfo {
    private String name;
    private AnimeListInfo animeListInfo;

    public UserInfo(String name, AnimeListInfo animeListInfo) {
        this.name = StringValidator.validate(name);
        this.animeListInfo = animeListInfo;
    }
}
