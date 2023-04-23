package com.github.he305.jbot.anime.domain.model.values;

import com.github.he305.jbot.anime.domain.model.enums.ListStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class ListInfo {
    private float listRating;
    private ListStatus listStatus;

    public ListInfo(ListStatus listStatus) {
        this.listStatus = listStatus;
        this.listRating = -1;
    }
}
