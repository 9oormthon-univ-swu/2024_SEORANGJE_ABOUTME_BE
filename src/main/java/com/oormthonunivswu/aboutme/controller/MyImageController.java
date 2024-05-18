package com.oormthonunivswu.aboutme.Controller;


import com.oormthonunivswu.aboutme.Dto.MyImageDTO;
import com.oormthonunivswu.aboutme.Service.MyImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
