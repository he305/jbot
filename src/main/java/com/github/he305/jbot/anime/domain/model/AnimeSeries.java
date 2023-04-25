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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(titleInfo.getMainTitle());
        sb.append("\ncurrent status: ");
        sb.append(airingStatus.label);
        if (date.getStartDate() != null) {
            sb.append(", start date: ");
            sb.append(date.getStartDate());
        }
        if (date.getEndDate() != null) {
            sb.append(", end date: ");
            sb.append(date.getEndDate());
        }

        sb.append(" url: ");
        sb.append(imageUrl);
        return sb.toString();
    }
}
