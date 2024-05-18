package com.oormthonunivswu.aboutme.dto;


import lombok.Getter;
import lombok.Setter;

public class DefaultImageDTO {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String category;
    @Getter
    @Setter
    private String originalFileName;
    @Getter
    @Setter
    private String serverFileName;
    @Getter
    @Setter
    private String filePath;

    public DefaultImageDTO(Long id, String category, String filePath) {
        this.id = id;
        this.category = category;
        this.filePath = filePath;
    }

}
