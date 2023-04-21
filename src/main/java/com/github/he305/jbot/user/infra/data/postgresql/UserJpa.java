package com.github.he305.jbot.user.infra.data.postgresql;

import com.github.he305.jbot.user.domain.model.enums.AnimeAudioSource;
import com.github.he305.jbot.user.domain.model.enums.AnimeSubsSource;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class UserJpa {
    @Id
    @Column(name = "id")
    private UUID id;

    @Embedded
    private UserInfoJpa userInfo;

    @Embedded
    private ChatInfoJpa chatInfo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<AnimeListInfoJpa> animeListInfoList = new ArrayList<>();

    @Enumerated
    @Column(name = "audio_source")
    private AnimeAudioSource audioSource;

    @Enumerated
    @Column(name = "subs_source")
    private AnimeSubsSource subsSource;

    public UserJpa(UUID id, UserInfoJpa userInfo, ChatInfoJpa chatInfo) {
        this.id = id;
        this.userInfo = userInfo;
        this.chatInfo = chatInfo;
    }

    public void addAnimeListInfoJpa(AnimeListInfoJpa animeListInfoJpa) {
        animeListInfoList.add(animeListInfoJpa);
    }
}
