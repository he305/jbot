package com.github.he305.jbot.anime.application.dtos.mal.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataDto {
    private MalAnimeListEntryDto node;
}
