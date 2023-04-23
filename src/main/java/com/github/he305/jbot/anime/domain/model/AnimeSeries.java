package com.github.he305.jbot.anime.domain.model;

import com.github.he305.jbot.anime.domain.model.enums.AiringStatus;
import com.github.he305.jbot.anime.domain.model.enums.ListStatus;
import com.github.he305.jbot.anime.domain.model.values.AiringDate;
import com.github.he305.jbot.anime.domain.model.values.ListInfo;
import com.github.he305.jbot.anime.domain.model.values.TitleInfo;
import lombok.*;

@Getter
@EqualsAndHashCode(of = "listId")
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class AnimeSeries {
    private int listId;
    private TitleInfo titleInfo;
    private AiringDate date;
    private ListInfo listInfo;
    private AiringStatus airingStatus;
    private String imageUrl;


    public AnimeSeries(int listId, TitleInfo titleInfo, AiringDate date, AiringStatus airingStatus, String imageUrl) {
        this(listId, titleInfo, date, new ListInfo(ListStatus.UNKNOWN), airingStatus, imageUrl);
    }
}
