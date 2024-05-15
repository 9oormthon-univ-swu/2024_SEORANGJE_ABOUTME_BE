package com.oormthonunivswu.aboutme.Repository;

import com.oormthonunivswu.aboutme.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
