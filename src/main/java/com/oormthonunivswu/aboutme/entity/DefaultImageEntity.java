package com.oormthonunivswu.aboutme.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "defaultimage")
public class DefaultImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    @Column(name = "origin_file_name")
    private String originFileName;
    @Column(name = "server_file_name")
    private String serverFileName;
    @Column(name = "file_path")
    private String filePath;

    @Builder
    public DefaultImageEntity(String category, String originFileName,
                         String serverFileName, String filePath) {
        this.category = category;
        this.originFileName = originFileName;
        this.serverFileName = serverFileName;
        this.filePath = filePath;
    }

}
