package org.youjhin.springhw5notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youjhin.springhw5notes.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
