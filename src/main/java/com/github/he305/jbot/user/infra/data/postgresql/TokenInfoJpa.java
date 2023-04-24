package com.github.he305.jbot.user.infra.data.postgresql;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class TokenInfoJpa {
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expiresAt;
}
