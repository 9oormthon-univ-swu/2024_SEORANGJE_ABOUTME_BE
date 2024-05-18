package com.oormthonunivswu.aboutme.controller;
import com.oormthonunivswu.aboutme.dto.MyImageSubjectDTO;
import com.oormthonunivswu.aboutme.service.MyImageSubjectService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public MyImageSubjectDTO getSubjects(@PathVariable Long user_id){
        return myImageSubjectService.getMyImageSubject(user_id);
    }
}
