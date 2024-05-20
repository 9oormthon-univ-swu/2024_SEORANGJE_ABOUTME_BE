package com.oormthonunivswu.aboutme.Repository;

import com.oormthonunivswu.aboutme.Entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity, Long>{
    Optional<ImageEntity> findByFilePath(String filePath);
}

