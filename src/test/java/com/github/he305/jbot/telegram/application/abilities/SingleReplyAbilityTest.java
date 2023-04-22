package com.github.he305.jbot.telegram.application.abilities;

import com.github.he305.jbot.telegram.application.actions.SingleReplyAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SingleReplyAbilityTest {

    @Mock
    private SingleReplyAction action;
    @Mock
    private AbilityBot bot;

    @InjectMocks
    private SingleReplyAbility underTest;

    @Test
    void ability() {
        String actionCommand = "test";
        Ability expected = Ability.builder()
                .name(actionCommand)
                .privacy(Privacy.PUBLIC)
                .locality(Locality.ALL)
                .action(ctx -> {
                })
                .build();

        Mockito.when(action.getCommand()).thenReturn(actionCommand);
        Ability actual = underTest.ability();
        assertEquals(expected, actual);
    }
}