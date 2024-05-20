package com.oormthonunivswu.aboutme.Dto;

import java.util.List;
import java.util.Map;

public class MyImageDetailDTO {

    private String guestNickname;
    private String imageComment;
    private Map<String, String> imageFilePaths;

    public MyImageDetailDTO(String guestNickname, String imageComment, Map<String, String> imageFilePaths) {
        this.guestNickname = guestNickname;
        this.imageComment = imageComment;
        this.imageFilePaths = imageFilePaths;
    }

    public String getGuestNickname() {
        return guestNickname;
    }

    public void setGuestNickname(String guestNickname) {
        this.guestNickname = guestNickname;
    }

    public String getImageComment() {
        return imageComment;
    }

    public void setImageComment(String imageComment) {
        this.imageComment = imageComment;
    }

    public Map<String, String> getImageFilePaths() {
        return imageFilePaths;
    }

    public void setImageFilePaths(Map<String, String> imageFilePaths) {
        this.imageFilePaths = imageFilePaths;
    }
}