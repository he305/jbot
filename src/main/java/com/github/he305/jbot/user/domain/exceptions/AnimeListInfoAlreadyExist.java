package com.github.he305.jbot.user.domain.exceptions;

import com.github.he305.jbot.common.exceptions.JBotException;
import com.github.he305.jbot.user.domain.model.entities.AnimeListInfo;

public class AnimeListInfoAlreadyExist extends JBotException {
    public AnimeListInfoAlreadyExist(AnimeListInfo info) {
        super(info.toString() + " already exists");
    }
}
