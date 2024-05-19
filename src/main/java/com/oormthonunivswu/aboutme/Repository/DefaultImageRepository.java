package com.oormthonunivswu.aboutme.Repository;


import com.oormthonunivswu.aboutme.Entity.DefaultImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefaultImageRepository extends JpaRepository<DefaultImageEntity, Long> {
    List<DefaultImageEntity> findByCategoryIn(List<String> categories);
}
