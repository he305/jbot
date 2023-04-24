package com.github.he305.jbot.anime.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MalApiConfig {
    @Value("${mal-client-id:none}")
    private String clientId;

    @Value("${mal-client-secret:none}")
    private String clientSecret;
}
