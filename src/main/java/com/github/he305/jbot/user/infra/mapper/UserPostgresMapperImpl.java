package com.github.he305.jbot.user.infra.mapper;

import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.infra.data.postgresql.UserJpa;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class UserPostgresMapperImpl implements UserPostgresMapper {
    private final ModelMapper mapper;

    public UserPostgresMapperImpl() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    @Override
    public UserJpa domainToData(User user) {
        return mapper.map(user, UserJpa.class);
    }

    @Override
    public User dataToDomain(UserJpa data) {
        return mapper.map(data, User.class);
    }
}
