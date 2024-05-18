package com.oormthonunivswu.aboutme.controller;

import com.oormthonunivswu.aboutme.dto.DefaultImageDTO;
import com.oormthonunivswu.aboutme.entity.DefaultImageEntity;
import com.oormthonunivswu.aboutme.entity.UserEntity;
import com.oormthonunivswu.aboutme.repository.UserRepository;
import com.oormthonunivswu.aboutme.service.DefaultImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/DefaultImage")
public class DefaultImageController {
    @Autowired
    private DefaultImageService defaultImageService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{user_id}")
    public List<DefaultImageDTO> getDefaultImages(@PathVariable Long user_id){
        UserEntity user = userRepository.findById(user_id).orElseThrow(()-> new RuntimeException("User not found"));
        List<DefaultImageEntity> defaultImageEntities = defaultImageService.getDefaultImagesByUserCategory(user);
        return defaultImageEntities.stream()
                .map(image -> new DefaultImageDTO(image.getId(), image.getCategory(), image.getFilePath()))
                .collect(Collectors.toList());

    }
}
