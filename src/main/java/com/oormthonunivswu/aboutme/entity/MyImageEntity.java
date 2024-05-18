package com.oormthonunivswu.aboutme.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "myimage")
public class MyImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "guest_nickname")
    private String guestNickname;

    @Column(name = "image_comment")
    private String imageComment;

    @Column(name = "flower")
    private Long flower;

    @Column (name = "animal")
    private Long animal;

    @Column(name = "season")
    private Long season;

    @Column (name = "color")
    private Long color;

    @Column(name = "charac")
    private Long charac;

    @Column (name = "place")
    private Long place;

    @Column(name = "food")
    private Long food;

    @Column (name = "hobby")
    private Long hobby;

    @Column(name = "job")
    private Long job;


    @Builder
    public MyImageEntity(UUID userId, String guestNickname, String imageComment,
                      Long flower, Long animal, Long season, Long color,
                      Long charac, Long place, Long food, Long hobby, Long job) {
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
    }
}
