package com.oormthonunivswu.aboutme.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class MyImageDetailDTO {
    private String guestNickname;
    private String imageComment;
    private Map<String,String>  imageFileName;
    private Map<String, String> imageFilePaths;

    public MyImageDetailDTO(String guestNickname, String imageComment, Map<String, String> imageFileName, Map<String, String> imageFilePaths) {
        this.guestNickname = guestNickname;
        this.imageComment = imageComment;
        this.imageFileName = imageFileName;
        this.imageFilePaths = imageFilePaths;
    }


}