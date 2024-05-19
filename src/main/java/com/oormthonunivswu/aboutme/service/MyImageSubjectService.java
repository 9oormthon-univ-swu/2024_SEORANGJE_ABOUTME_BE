package com.oormthonunivswu.aboutme.Service;

import com.oormthonunivswu.aboutme.Dto.MyImageSubjectDTO;
import com.oormthonunivswu.aboutme.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.UUID;

@Service
public class MyImageSubjectService {

    @Autowired
    private UserService userService;

    @Autowired
    private MyImageService myImageService;

    public MyImageSubjectDTO getMyImageSubject(Long id){
        User user = userService.findById(id);


        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다");
        }
        // 유저가 선택한 주제를 조회하고 MyImageSubjectDto로 변환하는 로직을 여기에 작성
        boolean flower = user.getFlower();
        boolean animal = user.getAnimal();
        boolean season = user.getSeason();
        boolean color = user.getColor();
        boolean charac = user.getCharac();
        boolean place = user.getPlace();
        boolean food = user.getFood();
        boolean hobby = user.getHobby();
        boolean job = user.getJob();

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
