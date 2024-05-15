package com.oormthonunivswu.aboutme.MyImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyImageRepository extends JpaRepository <MyImageEntity, Long>{
}
