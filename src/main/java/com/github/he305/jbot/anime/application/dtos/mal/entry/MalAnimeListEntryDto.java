package com.github.he305.jbot.anime.application.dtos.mal.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class MalAnimeListEntryDto {
    private int id;
    private String title;
    @JsonProperty(value = "main_picture")
    private PictureDto picture;
    @JsonProperty(value = "alternative_titles")
    private AlternativeTitlesDto alternativeTitlesDto;
    private BroadcastDto broadcast;
    private String status;
    @JsonProperty(value = "start_date")
    private String startDate;
    @JsonProperty(value = "end_date")
    private String endDate;
    @JsonProperty(value = "my_list_status")
    private MyListStatusDto listStatus;
    @JsonProperty(value = "num_episodes")
    private int numEpisodes;
}
