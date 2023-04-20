package com.github.he305.jbot.user.infra.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AnimeListInfoDataMongo {
    private String nickname;
    private String password;
}
