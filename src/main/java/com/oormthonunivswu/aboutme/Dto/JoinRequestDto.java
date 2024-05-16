package com.oormthonunivswu.aboutme.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinRequestDto {
    private String email;
    private String username;
    private String password;
    private Boolean flower;
    private Boolean animal;
    private Boolean season;
    private Boolean color;
    private Boolean charac;
    private Boolean place;
    private Boolean food;
    private Boolean hobby;
    private Boolean job;

}
