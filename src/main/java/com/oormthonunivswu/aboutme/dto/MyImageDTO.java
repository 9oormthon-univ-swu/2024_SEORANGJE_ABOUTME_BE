package com.oormthonunivswu.aboutme.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyImageDTO {
    private Long id;
    private String guestNickname;
    private String folderImageUrl;
    private String errorMessage;

    public MyImageDTO(Long id, String guestNickname, String folderImageUrl) {
        this.id = id;
        this.guestNickname = guestNickname;
        this.folderImageUrl = folderImageUrl;
    }

    // 에러 메시지를 포함한 생성자
    public MyImageDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
