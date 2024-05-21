package com.oormthonunivswu.aboutme.Dto;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DefaultImageDTO {

    private Long id;
    private String category;
    private String imageName;
    private String imageDetail;
    private String originalFileName;
    private String serverFileName;
    private String filePath;

    public DefaultImageDTO(Long id, String category, String imageName, String imageDetail,String filePath) {
        this.id = id;
        this.category = category;
        this.imageName = imageName;
        this.imageDetail = imageDetail;
        this.filePath = filePath;
    }
}
