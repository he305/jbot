package com.github.he305.jbot.anime.application.mappers;

import com.github.he305.jbot.anime.application.dtos.mal.entry.MalAnimeListEntryDto;
import com.github.he305.jbot.anime.domain.model.AnimeSeries;
import com.github.he305.jbot.anime.domain.model.enums.AiringStatus;
import com.github.he305.jbot.anime.domain.model.values.AiringDate;
import com.github.he305.jbot.anime.domain.model.values.TitleInfo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class MalAnimeEntryMapperImpl implements MalAnimeEntryMapper {
    private AiringStatus stringToAiringStatus(String status) {
        switch (status) {
            case "currently_airing" -> {
                return AiringStatus.AIRING;
            }
            case "finished_airing" -> {
                return AiringStatus.COMPLETE;
            }
            default -> {
                return AiringStatus.NOT_STARTED;
            }
        }
    }

    @Override
    public AnimeSeries dtoToDomain(MalAnimeListEntryDto dto) {
        Set<String> synonyms = new HashSet<>(Arrays.asList(dto.getAlternativeTitlesDto().getSynonyms()));
        synonyms.add(dto.getAlternativeTitlesDto().getEn());
        synonyms.add(dto.getAlternativeTitlesDto().getJa());

        LocalDate startDate = dto.getStartDate() == null ? null : LocalDate.parse(dto.getStartDate());
        LocalDate endDate = dto.getEndDate() == null ? null : LocalDate.parse(dto.getEndDate());

        return new AnimeSeries(
                dto.getId(),
                new TitleInfo(dto.getTitle(), synonyms),
                new AiringDate(startDate, endDate),
                stringToAiringStatus(dto.getStatus()),
                dto.getPicture().getLarge()
        );
    }
}
