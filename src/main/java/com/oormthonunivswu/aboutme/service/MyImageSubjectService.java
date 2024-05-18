package com.oormthonunivswu.aboutme.service;

import com.oormthonunivswu.aboutme.dto.MyImageSubjectDTO;
import com.oormthonunivswu.aboutme.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Service
public class MyImageSubjectService {

    @Autowired
    private UserService userService;

    @Autowired
    private MyImageService myImageService;

    public MyImageSubjectDTO getMyImageSubject(Long id){
        UserEntity user = userService.findById(id);


        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다");
        }
        // 유저가 선택한 주제를 조회하고 MyImageSubjectDto로 변환하는 로직을 여기에 작성
        boolean flower = user.isFlower();
        boolean animal = user.isAnimal();
        boolean season = user.isSeason();
        boolean color = user.isColor();
        boolean charac = user.isCharac();
        boolean place = user.isPlace();
        boolean food = user.isFood();
        boolean hobby = user.isHobby();
        boolean job = user.isJob();

        // MyImageSubjectDto 객체 생성 및 반환
        MyImageSubjectDTO dto = new MyImageSubjectDTO();
        dto.setSubjects(Map.of(
                "flower", flower,
                "animal", animal,
                "season", season,
                "color", color,
                "charac", charac,
                "place", place,
                "food", food,
                "hobby", hobby,
                "job", job
        ));

        return dto;
    }
}
