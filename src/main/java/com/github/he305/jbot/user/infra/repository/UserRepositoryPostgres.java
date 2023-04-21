package com.github.he305.jbot.user.infra.repository;

import com.github.he305.jbot.user.domain.abstractions.UserRepository;
import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.infra.data.postgresql.UserJpa;
import com.github.he305.jbot.user.infra.jpa.UserPostgresJpa;
import com.github.he305.jbot.user.infra.mapper.UserPostgresMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryPostgres implements UserRepository {

    private final UserPostgresJpa userPostgresJpa;
    private final UserPostgresMapper mapper;

    @Override
    public List<User> getAll() {
        List<UserJpa> userJpaList = userPostgresJpa.findAll();
        return userJpaList.stream().map(mapper::dataToDomain).toList();
    }

    @Override
    public void save(User user) {
        UserJpa userJpa = mapper.domainToData(user);
        userPostgresJpa.save(userJpa);
    }
}
