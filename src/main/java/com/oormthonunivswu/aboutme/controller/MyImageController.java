package com.oormthonunivswu.aboutme.Controller;


import com.oormthonunivswu.aboutme.Dto.DefaultImageDTO;
import com.oormthonunivswu.aboutme.Dto.MyImageDTO;
import com.oormthonunivswu.aboutme.Entity.DefaultImageEntity;
import com.oormthonunivswu.aboutme.Entity.User;
import com.oormthonunivswu.aboutme.Repository.UserRepository;
import com.oormthonunivswu.aboutme.Service.DefaultImageService;
import com.oormthonunivswu.aboutme.Service.MyImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/MyImage")
public class MyImageController {

    @Autowired
    private MyImageService myImageService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DefaultImageService defaultImageService;

    @GetMapping("/{user_id}")
    public List<MyImageDTO> getAllMyImagesByUserId(@PathVariable User user_id){
        return myImageService.getAllMyImagesByUserId(user_id);
    }
    public List<DefaultImageDTO> getDefaultImages(@PathVariable Long user_id){
        User user = userRepository.findById(user_id).orElseThrow(()-> new RuntimeException("User not found"));
        List<DefaultImageEntity> defaultImageEntities = defaultImageService.getDefaultImagesByUserCategory(user);
        return defaultImageEntities.stream()
                .map(image -> new DefaultImageDTO(image.getId(), image.getCategory(), image.getFilePath()))
                .collect(Collectors.toList());

    }
}
