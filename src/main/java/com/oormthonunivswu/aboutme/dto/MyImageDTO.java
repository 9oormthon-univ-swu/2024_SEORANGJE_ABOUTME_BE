package com.oormthonunivswu.aboutme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyImageDTO {
    private Long id;
    private String guestNickname;
    private String imageComment;
}
