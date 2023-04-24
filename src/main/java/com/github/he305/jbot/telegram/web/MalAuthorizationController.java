package com.github.he305.jbot.telegram.web;

import com.github.he305.jbot.telegram.application.services.AnimeListAuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mal_auth")
@RequiredArgsConstructor
@Slf4j
public class MalAuthorizationController {
    private final AnimeListAuthorizationService authorizationService;

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String authorize(@RequestParam String code, @RequestParam int state) {
        String strState = String.valueOf(state);
        authorizationService.authorize(code, strState);

        return """
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
    }
}
