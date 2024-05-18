package com.oormthonunivswu.aboutme.repository;

import com.oormthonunivswu.aboutme.entity.DefaultImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefaultImageRepository extends JpaRepository<DefaultImageEntity, Long> {
    List<DefaultImageEntity> findByCategoryIn(List<String> categories);
}
