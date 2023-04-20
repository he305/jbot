package com.github.he305.jbot.user.infra.jpa;

import com.github.he305.jbot.user.infra.data.UserDataMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserJpa extends MongoRepository<UserDataMongo, String> {
}
