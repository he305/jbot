package com.github.he305.jbot.user.domain.model;

import com.github.he305.jbot.common.enums.AnimeAudioSource;
import com.github.he305.jbot.common.enums.AnimeSubsSource;
import com.github.he305.jbot.user.domain.exceptions.AnimeListInfoAlreadyExist;
import com.github.he305.jbot.user.domain.model.entities.AnimeListInfo;
import com.github.he305.jbot.user.domain.model.enums.AnimeListType;
import com.github.he305.jbot.user.domain.model.values.ChatInfo;
import com.github.he305.jbot.user.domain.model.values.UserInfo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class User {
    private UUID id;
    private UserInfo userInfo;
    private ChatInfo chatInfo;
    private AnimeAudioSource audioSource = AnimeAudioSource.JAPANESE;

    private AnimeSubsSource subsSource = AnimeSubsSource.NONE;

    private List<AnimeListInfo> animeListInfoList;

    public User(UUID id, UserInfo userInfo, ChatInfo chatInfo) {
        this.id = id;
        this.userInfo = userInfo;
        this.chatInfo = chatInfo;
        this.animeListInfoList = new ArrayList<>();
    }

    public static User create(UserInfo userInfo, ChatInfo chatInfo) {
        UUID id = UUID.randomUUID();

        return new User(id, userInfo, chatInfo);
    }

    public static User getExistent(UUID id, UserInfo userInfo, ChatInfo chatInfo) {
        return new User(id, userInfo, chatInfo);
    }

    public void addAnimeListInfo(AnimeListInfo info) {
        if (animeListInfoList.stream().anyMatch(existing -> existing.getAnimeListType().equals(info.getAnimeListType()))) {
            throw new AnimeListInfoAlreadyExist(info);
        }

        animeListInfoList.add(info);
    }

    public boolean animeListInfoWithTypeExists(AnimeListType type) {
        return animeListInfoList.stream().anyMatch(al -> al.getAnimeListType().equals(type));
    }

    public boolean removeAnimeListInfoType(AnimeListType type) {
        Optional<AnimeListInfo> animeListInfo = animeListInfoList.stream().filter(al -> al.getAnimeListType().equals(type)).findFirst();
        return animeListInfo.map(info -> animeListInfoList.remove(info)).orElse(false);
    }

    public void setAudioSource(AnimeAudioSource newSource) {
        this.audioSource = newSource;
    }

    public void setSubsSource(AnimeSubsSource newSource) {
        this.subsSource = newSource;
    }
}
