package com.github.he305.jbot.telegram.web;

import com.github.he305.jbot.telegram.application.services.AnimeListAuthorizationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MalAuthorizationControllerTest {

    @Mock
    private AnimeListAuthorizationService authorizationService;

    @InjectMocks
    private MalAuthorizationController underTest;

    @Test
    void authorize() {
        String expected = """
                <!DOCTYPE html>
                <html>
                <head>
                <script>
                window.close();
                </script>
                </head>
                <body>
                </body>
                </html>
                """;

        String actual = underTest.authorize("code", 1234);
        assertEquals(expected, actual);
    }
}