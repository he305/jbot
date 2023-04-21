package com.github.he305.jbot.user.domain.model.entities;

import com.github.he305.jbot.common.validators.StringValidator;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@ToString
public class AnimeListInfo {
    private UUID id;
    private String nickname;
    private String password;
    private AnimeListType animeListType;

    public AnimeListInfo(String nickname, String password, AnimeListType type) {
        this.id = UUID.randomUUID();

        this.nickname = StringValidator.validate(nickname);
        this.password = StringValidator.validate(password);
        this.animeListType = type;
    }

    public AnimeListInfo(UUID id, String nickname, String password, AnimeListType type) {
        this.id = id;
        this.nickname = StringValidator.validate(nickname);
        this.password = StringValidator.validate(password);
        this.animeListType = type;
    }

    public void changePassword(String newPassword) {
        this.password = StringValidator.validate(newPassword);
    }
}
