package com.oormthonunivswu.aboutme.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "myimage")
public class MyImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "guest_nickname")
    private String guestNickname;

    @Column(name = "image_comment")
    private String imageComment;

    @Column(name = "folder_image_url")
    private String folderImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flower")
    private ImageEntity flower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal")
    private ImageEntity animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season")
    private ImageEntity season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color")
    private ImageEntity color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charac")
    private ImageEntity charac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place")
    private ImageEntity place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food")
    private ImageEntity food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hobby")
    private ImageEntity hobby;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job")
    private ImageEntity job;


    @Builder
    public MyImageEntity(User userId, String guestNickname, String imageComment,
                         ImageEntity flower, ImageEntity animal, ImageEntity season, ImageEntity color,
                         ImageEntity charac, ImageEntity place, ImageEntity food, ImageEntity hobby, ImageEntity job,
                         String folderImageUrl) {
        this.userId = userId;
        this.guestNickname = guestNickname;
        this.imageComment = imageComment;
        this.flower = flower;
        this.animal = animal;
        this.season = season;
        this.color = color;
        this.charac = charac;
        this.place = place;
        this.food = food;
        this.hobby = hobby;
        this.job = job;
        this.folderImageUrl=folderImageUrl;
    }
}
