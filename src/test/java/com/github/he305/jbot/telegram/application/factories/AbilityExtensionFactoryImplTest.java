package com.github.he305.jbot.telegram.application.factories;

import com.github.he305.jbot.telegram.application.actions.SingleReplyAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.util.AbilityExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AbilityExtensionFactoryImplTest {

    @Mock
    SingleReplyAction mockAction;

    private AbilityExtensionFactoryImpl underTest;

    @BeforeEach
    void init() {
        underTest = new AbilityExtensionFactoryImpl(List.of(mockAction));
    }

    @Test
    void getAbilityExtensions() {
        AbilityBot bot = Mockito.mock(AbilityBot.class);
        List<AbilityExtension> actual = underTest.getAbilityExtensions(bot);

        assertEquals(1, actual.size());
    }
}