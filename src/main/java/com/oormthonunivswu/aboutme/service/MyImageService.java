package com.oormthonunivswu.aboutme.Service;


import com.oormthonunivswu.aboutme.Dto.MyImageDTO;
import com.oormthonunivswu.aboutme.Entity.MyImageEntity;
import com.oormthonunivswu.aboutme.Entity.User;
import com.oormthonunivswu.aboutme.Repository.MyImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyImageService {

    @Autowired
    private MyImageRepository myImageRepository;

    public List<MyImageDTO> getAllMyImagesByUserId(User user_id){
        List<MyImageEntity> myImageEntities = myImageRepository.findByUserId(user_id);
        // myImageEntities가 null이면 이미지가 생성되지 않았음을 프론트엔드로 알려줍니다.
        if (myImageEntities == null || myImageEntities.isEmpty()) {
            return Collections.singletonList(new MyImageDTO("사용자의 게시판에 마이이미지가 생성되지 않았습니다."));
        }

        // 그 외의 경우에는 이미지 목록을 반환합니다.
        return myImageEntities.stream()
                .map(image-> new MyImageDTO(image.getId(), image.getGuestNickname(),image.getImageComment()))
                .collect(Collectors.toList());
    }
}
