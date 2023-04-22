package com.github.he305.jbot.user.infra.repository;

import com.github.he305.jbot.user.domain.abstractions.UserRepository;
import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.domain.model.values.ChatInfo;
import com.github.he305.jbot.user.infra.data.postgresql.ChatInfoJpa;
import com.github.he305.jbot.user.infra.data.postgresql.UserJpa;
import com.github.he305.jbot.user.infra.jpa.UserPostgresJpa;
import com.github.he305.jbot.user.infra.mapper.UserPostgresMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> getByChatId(ChatInfo chatInfo) {
        ChatInfoJpa chatInfoJpa = new ChatInfoJpa(chatInfo.getChatId());
        return userPostgresJpa.findByChatInfo(chatInfoJpa).map(mapper::dataToDomain);
    }

    @Override
    public void save(User user) {
        UserJpa userJpa = mapper.domainToData(user);
        userPostgresJpa.save(userJpa);
    }
}
