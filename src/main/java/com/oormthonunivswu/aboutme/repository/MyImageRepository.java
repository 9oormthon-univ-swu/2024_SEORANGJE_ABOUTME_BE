package com.oormthonunivswu.aboutme.repository;

import com.oormthonunivswu.aboutme.entity.MyImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyImageRepository extends JpaRepository <MyImageEntity, Long>{
    List<MyImageEntity> findByUserId(Long id);
}
