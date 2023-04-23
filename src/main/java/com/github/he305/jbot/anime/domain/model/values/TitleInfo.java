package com.github.he305.jbot.anime.domain.model.values;

import com.github.he305.jbot.common.validators.StringValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class TitleInfo {
    private String mainTitle;
    private Set<String> synonyms;

    public TitleInfo(String mainTitle, Set<String> synonyms) {
        this.mainTitle = StringValidator.validate(mainTitle);
        this.synonyms = new HashSet<>(synonyms);
    }

    public Set<String> getTitlesAsSet() {
        Set<String> allTitles = new HashSet<>(synonyms);
        allTitles.add(mainTitle);
        return allTitles;
    }

    public String getTitlesAsString() {
        Set<String> allTitles = getTitlesAsSet();
        return String.join(" ", allTitles);
    }
}
