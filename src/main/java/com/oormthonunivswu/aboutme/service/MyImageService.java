package com.oormthonunivswu.aboutme.service;

import com.oormthonunivswu.aboutme.dto.MyImageDTO;
import com.oormthonunivswu.aboutme.entity.MyImageEntity;
import com.oormthonunivswu.aboutme.repository.MyImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyImageService {

    @Autowired
    private MyImageRepository myImageRepository;

    public List<MyImageDTO> getAllMyImagesByUserId(Long user_id){
        List<MyImageEntity> myImageEntities = myImageRepository.findByUserId(user_id);
        return myImageEntities.stream()
                .map(image-> new MyImageDTO(image.getId(), image.getGuestNickname(),image.getImageComment()))
                .collect(Collectors.toList());
    }
}
