package com.github.he305.jbot.user.domain.model.values;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expiresAt;

    public TokenInfo(String accessToken, String refreshToken, int expiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;

        LocalDateTime now = LocalDateTime.now();
        this.expiresAt = now.plus(expiresIn, ChronoUnit.SECONDS).minus(30, ChronoUnit.MINUTES);
    }
}
