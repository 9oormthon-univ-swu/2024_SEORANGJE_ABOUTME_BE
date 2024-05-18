package com.oormthonunivswu.aboutme.controller;

import com.oormthonunivswu.aboutme.dto.MyImageDTO;
import com.oormthonunivswu.aboutme.service.MyImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/MyImage")
public class MyImageController {

    @Autowired
    private MyImageService myImageService;

    @GetMapping("/{user_id}")
    public List<MyImageDTO> getAllMyImagesByUserId(@PathVariable Long user_id){
        return myImageService.getAllMyImagesByUserId(user_id);
    }
}
