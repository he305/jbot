package com.github.he305.jbot.telegram.application.actions;

import com.github.he305.jbot.telegram.application.services.GetUserByContextQuery;
import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.domain.model.values.UserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.abilitybots.api.objects.MessageContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class StartActionTest {

    @Mock
    private GetUserByContextQuery getUserByContextQuery;
    @InjectMocks
    private StartAction underTest;

    @Test
    void getCommand() {
        String expected = "start";
        assertEquals(expected, underTest.getCommand());
    }

    @Test
    void getDescription() {
        String expected = "registration command";
        assertEquals(expected, underTest.getDescription());
    }

    @Test
    void getMessage() {
        String name = "Sample";
        String expected = String.format("Hello, %s! Feel free to use /help command", name);

        MessageContext ctx = Mockito.mock(MessageContext.class);
        UserInfo info = new UserInfo(name);
        User user = Mockito.mock(User.class);
        Mockito.when(user.getUserInfo()).thenReturn(info);
        Mockito.when(getUserByContextQuery.execute(ctx)).thenReturn(user);

        String actual = underTest.getMessage(ctx);
        assertEquals(expected, actual);
    }
}