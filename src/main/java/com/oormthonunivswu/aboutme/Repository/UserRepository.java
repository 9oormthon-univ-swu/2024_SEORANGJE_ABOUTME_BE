package com.oormthonunivswu.aboutme.Repository;

import com.oormthonunivswu.aboutme.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    Optional<User> findById(UUID userId);
}
