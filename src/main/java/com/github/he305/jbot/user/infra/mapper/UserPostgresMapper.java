package com.github.he305.jbot.user.infra.mapper;

import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.infra.data.postgresql.UserJpa;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface UserPostgresMapper {

    UserJpa domainToData(User user);

    User dataToDomain(UserJpa data);
}
