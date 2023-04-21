package com.github.he305.jbot.user.infra.data.postgresql;


import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class UserInfoJpa {
    private String name;
}
