package com.oormthonunivswu.aboutme.service;

import com.oormthonunivswu.aboutme.dto.DefaultImageDTO;
import com.oormthonunivswu.aboutme.entity.UserEntity;
import com.oormthonunivswu.aboutme.entity.DefaultImageEntity;
import com.oormthonunivswu.aboutme.repository.DefaultImageRepository;
import com.oormthonunivswu.aboutme.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultImageService {
    private static final Logger log = LoggerFactory.getLogger(DefaultImageService.class);
    @Autowired
    private DefaultImageRepository defaultImageRepository;

    public List<DefaultImageEntity> getDefaultImagesByUserCategory(UserEntity user){
        List<String> categories = new ArrayList<>();
        if (user.isFlower()) categories.add("flower");
        if (user.isAnimal()) categories.add("animal");
        if (user.isSeason()) categories.add("season");
        if (user.isColor()) categories.add("color");
        if (user.isCharac()) categories.add("charac");
        if (user.isPlace()) categories.add("place");
        if (user.isFood()) categories.add("food");
        if (user.isHobby()) categories.add("hobby");
        if (user.isJob()) categories.add("job");
        return defaultImageRepository.findByCategoryIn(categories);


    }
}