package com.oormthonunivswu.aboutme.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Data
@AllArgsConstructor
public class MyImageRequestDTO {
    private String guestNickname;
    private String imageComment;
    @Setter
    @Getter
    private Map<String, MultipartFile> imageFiles; // Key: subject, Value: image file
    @Setter
    @Getter
    private Map<String, String> imageUrls; // Key: subject, Value: image URL or file name

}

