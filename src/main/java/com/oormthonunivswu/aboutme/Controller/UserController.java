package com.oormthonunivswu.aboutme.Controller;

import com.oormthonunivswu.aboutme.Dto.JoinRequestDto;
import com.oormthonunivswu.aboutme.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping("/api/join")
    public String join(@RequestBody JoinRequestDto joinRequestDto) {

        return userService.join(joinRequestDto);
    }
}
