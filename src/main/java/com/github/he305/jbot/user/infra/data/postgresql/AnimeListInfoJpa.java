package com.github.he305.jbot.user.infra.data.postgresql;

import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "anime_list_info")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class AnimeListInfoJpa {
    @Id
    @Column
    private UUID id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "authorization_code", columnDefinition = "TEXT")
    private String authorizationCode;

    @Enumerated
    @Column(name = "type")
    private AnimeListType animeListType;

    public AnimeListInfoJpa(UUID id, String nickname, String password, AnimeListType animeListType) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.animeListType = animeListType;
    }
}
