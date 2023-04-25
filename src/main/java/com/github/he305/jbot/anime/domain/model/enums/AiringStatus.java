package com.github.he305.jbot.anime.domain.model.enums;

public enum AiringStatus {
    AIRING("airing"),
    COMPLETE("completed"),
    NOT_STARTED("not started");

    public final String label;

    AiringStatus(String label) {
        this.label = label;
    }
}
