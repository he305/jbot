package com.github.he305.jbot.user.infra.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "users")
public class UserDataMongo {
    @Id
    private String id;

    private UUID userId;
    private UserInfoDataMongo userInfo;
    private ChatInfoDataMongo chatInfo;
}
