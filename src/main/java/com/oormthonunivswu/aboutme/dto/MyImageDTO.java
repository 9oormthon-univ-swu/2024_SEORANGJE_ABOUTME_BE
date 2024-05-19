package com.oormthonunivswu.aboutme.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyImageDTO {
    private Long id;
    private String guestNickname;
    private String imageComment;
    private String errorMessage;

    public MyImageDTO(Long id, String guestNickname, String imageComment) {
        this.id = id;
        this.guestNickname = guestNickname;
        this.imageComment = imageComment;
    }

    // 에러 메시지를 포함한 생성자
    public MyImageDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
