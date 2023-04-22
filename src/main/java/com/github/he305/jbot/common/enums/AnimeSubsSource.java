package com.github.he305.jbot.common.enums;

public enum AnimeSubsSource {
    NONE(Language.NONE),
    JAPANESE(Language.JAPANESE),
    SUBSPLEASE(Language.ENGLISH);

    public final Language label;

    AnimeSubsSource(Language label) {
        this.label = label;
    }
}
