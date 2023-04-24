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
public class BroadcastDto {
    @JsonProperty(value = "day_of_the_week")
    private String weekDay;
    @JsonProperty(value = "start_time")
    private String startTime;
}
