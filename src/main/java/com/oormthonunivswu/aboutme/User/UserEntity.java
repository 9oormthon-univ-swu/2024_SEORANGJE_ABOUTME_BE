package com.oormthonunivswu.aboutme.User;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true)
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "flower")
    private boolean flower;

    @Column(name = "animal")
    private boolean animal;

    @Column(name = "season")
    private boolean season;

    @Column(name = "color")
    private boolean color;

    @Column(name = "charac")
    private boolean charac;

    @Column(name = "place")
    private boolean place;

    @Column(name = "food")
    private boolean food;

    @Column(name = "hobby")
    private boolean hobby;

    @Column(name = "job")
    private boolean job;

    @Column(name = "url")
    private String url;

    @Builder
    public UserEntity(String userId, String password, String nickname,
                boolean flower, boolean animal, boolean season, boolean color,
                boolean charac, boolean place, boolean food, boolean hobby, boolean job) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
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
