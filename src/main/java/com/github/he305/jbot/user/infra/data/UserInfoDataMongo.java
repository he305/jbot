package com.github.he305.jbot.user.infra.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserInfoDataMongo {
    private String name;
    private AnimeListInfoDataMongo animeListInfo;
}
