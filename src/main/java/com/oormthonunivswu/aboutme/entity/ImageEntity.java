package com.oormthonunivswu.aboutme.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "image")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    @Column(name = "original_file_name")
    private String originalFileName;
    @Column(name = "server_file_name")
    private String serverFileName;
    @Column(name = "Image_detail")
    private String ImageDetail;
    @Column(name = "Image_name")
    private String ImageName;
    @Column(name = "file_path")
    private String filePath;

    @Builder
    public ImageEntity(String category, String originalFileName, String serverFileName, String ImageDetail, String ImageName, String filePath) {
        this.category = category;
        this.originalFileName=originalFileName;
        this.serverFileName=getServerFileName();
        this.ImageName=getImageName();
        this.ImageDetail=getImageDetail();
        this.filePath = filePath;
    }
}
