package com.oormthonunivswu.aboutme.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "image")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orginalFileName;
    private String serverFileName;
    private String filePath;
}
