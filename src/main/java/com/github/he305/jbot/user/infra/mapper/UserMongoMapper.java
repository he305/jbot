package com.github.he305.jbot.user.infra.mapper;

import com.github.he305.jbot.user.domain.model.User;
import com.github.he305.jbot.user.infra.data.UserDataMongo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMongoMapper {

    List<User> dataToDomainCollection(List<UserDataMongo> userDataMongoList);

    @Mapping(target = "id", ignore = true)
    UserDataMongo domainToData(User user);

    User dataToDomain(UserDataMongo data);
}
