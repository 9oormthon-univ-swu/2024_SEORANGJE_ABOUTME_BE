package com.oormthonunivswu.aboutme.Entity;

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
