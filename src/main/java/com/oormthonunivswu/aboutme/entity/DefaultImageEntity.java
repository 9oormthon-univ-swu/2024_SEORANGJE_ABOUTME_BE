package com.oormthonunivswu.aboutme.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name = "defaultimage")
@Table(name = "image")
public class DefaultImageEntity {
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
    public DefaultImageEntity(String category, String originalFileName, String serverFileName, String ImageDetail, String ImageName, String filePath) {
        this.category = category;
        this.originalFileName=originalFileName;
        this.serverFileName=getServerFileName();
        this.ImageName=getImageName();
        this.ImageDetail=getImageDetail();
        this.filePath = filePath;
    }

}
