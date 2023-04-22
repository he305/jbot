package com.github.he305.jbot.telegram.application.actions;

import com.github.he305.jbot.common.enums.AnimeAudioSource;
import com.github.he305.jbot.common.enums.AnimeSubsSource;
import com.github.he305.jbot.common.enums.Language;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.objects.MessageContext;

import java.util.Arrays;

@Component
public class GetAllPreferencesAction implements SingleReplyAction {
    private static void fillLanguageData(String actual, Language language, StringBuilder sb) {
        String label = String.valueOf(language).toLowerCase();
        sb.append(actual);
        if (!label.equals(actual) && !language.equals(Language.NONE)) {
            sb.append(String.format(" (%s)", label));
        }
        sb.append("\n");
    }

    @Override
    public String getCommand() {
        return "get_all_preferences";
    }

    @Override
    public String getDescription() {
        return "display all possible anime source preferences";
    }

    @Override
    public String getMessage(MessageContext context) {
        AnimeAudioSource[] animeAudioSources = AnimeAudioSource.values();
        AnimeSubsSource[] animeSubsSources = AnimeSubsSource.values();

        StringBuilder sb = new StringBuilder();
        sb.append("Anime audio sources:\n");
        Arrays.stream(animeAudioSources).forEach(audioSource -> {
            String actual = String.valueOf(audioSource).toLowerCase();
            fillLanguageData(actual, audioSource.label, sb);
        });

        sb.append("\nAnime subs sources:\n");
        Arrays.stream(animeSubsSources).forEach(subsSource -> {
            String actual = String.valueOf(subsSource).toLowerCase();
            fillLanguageData(actual, subsSource.label, sb);
        });

        return sb.toString();
    }
}
