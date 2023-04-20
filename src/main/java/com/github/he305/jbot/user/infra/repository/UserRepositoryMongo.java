package com.github.he305.jbot.user.infra.repository;

import com.github.he305.jbot.user.domain.abstractions.UserRepository;
import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.infra.data.UserDataMongo;
import com.github.he305.jbot.user.infra.jpa.UserJpa;
import com.github.he305.jbot.user.infra.mapper.UserMongoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryMongo implements UserRepository {
    private final UserJpa userJpa;
    private final UserMongoMapper mapper;

    @Override
    public List<User> getAll() {
        List<UserDataMongo> userDataMongoList = userJpa.findAll();
        return mapper.dataToDomainCollection(userDataMongoList);
    }

    @Override
    public void save(User user) {
        UserDataMongo userDataMongo = mapper.domainToData(user);
        userJpa.save(userDataMongo);
    }
}
