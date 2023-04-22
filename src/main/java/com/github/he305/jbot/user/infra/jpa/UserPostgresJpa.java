package com.github.he305.jbot.user.infra.jpa;

import com.github.he305.jbot.user.infra.data.postgresql.ChatInfoJpa;
import com.github.he305.jbot.user.infra.data.postgresql.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserPostgresJpa extends JpaRepository<UserJpa, UUID> {
    Optional<UserJpa> findByChatInfo(ChatInfoJpa chatInfoJpa);
}
