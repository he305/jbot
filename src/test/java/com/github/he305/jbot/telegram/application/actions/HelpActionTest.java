package com.github.he305.jbot.telegram.application.actions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.abilitybots.api.objects.MessageContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HelpActionTest {

    private final List<BotAction> actions = List.of(
            new BotAction() {
                @Override
                public String getCommand() {
                    return "1";
                }

                @Override
                public String getDescription() {
                    return "disc1";
                }
            },
            new BotAction() {
                @Override
                public String getCommand() {
                    return "2";
                }

                @Override
                public String getDescription() {
                    return "disc2";
                }
            }
    );
    private final HelpAction underTest = new HelpAction(actions);
    @Mock
    private MessageContext context;

    @Test
    void getCommand() {
        String expected = "help";
        assertEquals(expected, underTest.getCommand());
    }

    @Test
    void getDescription() {
        String expected = "display all commands";
        assertEquals(expected, underTest.getDescription());
    }

    @Test
    void getMessage() {
        String expected = String.format("/help - display all commands%n/1 - disc1%n/2 - disc2%n");
        String actual = underTest.getMessage(context);

        assertEquals(expected, actual);
    }
}