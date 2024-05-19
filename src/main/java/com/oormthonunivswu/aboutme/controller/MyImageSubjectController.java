package com.oormthonunivswu.aboutme.Controller;


import com.oormthonunivswu.aboutme.Dto.MyImageSubjectDTO;
import com.oormthonunivswu.aboutme.Service.MyImageSubjectService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
public class MyImageSubjectController {
    public static Logger getLog() {
        return log;
    }
    @Autowired
    private MyImageSubjectService myImageSubjectService;

    //MyImageSubject 반환 API
    @GetMapping("/MyImageSubject/{user_id}")
    public MyImageSubjectDTO getSubjects(@PathVariable UUID user_id){
        return myImageSubjectService.getMyImageSubject(user_id);
    }
}
