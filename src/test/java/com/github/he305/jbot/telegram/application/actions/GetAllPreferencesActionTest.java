package com.github.he305.jbot.telegram.application.actions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.abilitybots.api.objects.MessageContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GetAllPreferencesActionTest {

    private final GetAllPreferencesAction underTest = new GetAllPreferencesAction();

    @Test
    void getCommand() {
        String expected = "get_all_preferences";
        assertEquals(expected, underTest.getCommand());
    }

    @Test
    void getDescription() {
        String expected = "display all possible anime source preferences";
        assertEquals(expected, underTest.getDescription());
    }

    @Test
    void getMessage() {
        String expected = """
                Anime audio sources:
                japanese
                                
                Anime subs sources:
                none
                japanese
                subsplease (english)
                """;

        MessageContext ctx = Mockito.mock(MessageContext.class);

        String actual = underTest.getMessage(ctx);
        assertEquals(expected, actual);
    }
}