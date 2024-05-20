package com.oormthonunivswu.aboutme.Repository;


import com.oormthonunivswu.aboutme.Entity.MyImageEntity;
import com.oormthonunivswu.aboutme.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyImageRepository extends JpaRepository<MyImageEntity, Long> {
    List<MyImageEntity> findByUserId(User id);
}
