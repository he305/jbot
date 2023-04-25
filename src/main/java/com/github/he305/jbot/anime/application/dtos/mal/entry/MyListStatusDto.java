package com.github.he305.jbot.anime.application.dtos.mal.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyListStatusDto {
    private String status;
    private int score;
    @JsonProperty(value = "num_episodes_watched")
    private int numEpisodesWatched;
    @JsonProperty(value = "is_rewatching")
    private boolean isRewatching;
    @JsonProperty(value = "updated_at")
    private LocalDateTime updatedAt;
}
