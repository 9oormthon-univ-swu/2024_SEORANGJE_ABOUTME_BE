package com.oormthonunivswu.aboutme.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // IDENTITY로 변경
    private Long id;

    @Column(columnDefinition = "BINARY(16)", unique = true)
    private UUID uuid;
    private String username;
    private String email;
    private String password;
    @Column(name = "flower")
    private Boolean flower;

    @Column(name = "animal")
    private Boolean animal;

    @Column(name = "season")
    private Boolean season;

    @Column(name = "color")
    private Boolean color;

    @Column(name = "charac")
    private Boolean charac;

    @Column(name = "place")
    private Boolean place;

    @Column(name = "food")
    private Boolean food;

    @Column(name = "hobby")
    private Boolean hobby;

    @Column(name = "job")
    private Boolean job;

    @Column(name = "url")
    private String url;
}
