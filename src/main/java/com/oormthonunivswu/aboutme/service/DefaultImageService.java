package com.oormthonunivswu.aboutme.Service;

import com.oormthonunivswu.aboutme.Entity.DefaultImageEntity;
import com.oormthonunivswu.aboutme.Entity.User;
import com.oormthonunivswu.aboutme.Repository.DefaultImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultImageService {
    private static final Logger log = LoggerFactory.getLogger(DefaultImageService.class);
    @Autowired
    private DefaultImageRepository defaultImageRepository;

    public List<DefaultImageEntity> getDefaultImagesByUserCategory(User user){
        List<String> categories = new ArrayList<>();
        if (user.getFlower()) categories.add("flower");
        if (user.getAnimal()) categories.add("animal");
        if (user.getSeason()) categories.add("season");
        if (user.getColor()) categories.add("color");
        if (user.getCharac()) categories.add("charac");
        if (user.getPlace()) categories.add("place");
        if (user.getFood()) categories.add("food");
        if (user.getHobby()) categories.add("hobby");
        if (user.getJob()) categories.add("job");
        return defaultImageRepository.findByCategoryIn(categories);


    }
}