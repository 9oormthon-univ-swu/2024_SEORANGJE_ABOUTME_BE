package com.oormthonunivswu.aboutme.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDefaultImageDTO {
    private Long id;
    private String username;
    private List<DefaultImageDTO> defaultImages;

    public UserDefaultImageDTO(Long id, String username, List<DefaultImageDTO> defaultImages) {
        this.id = id;
        this.username = username;
        this.defaultImages = defaultImages;
    }
}
