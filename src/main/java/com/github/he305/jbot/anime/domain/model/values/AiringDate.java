package com.github.he305.jbot.anime.domain.model.values;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class AiringDate {
    private LocalDate startDate;
    private LocalDate endDate;

    public AiringDate(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
