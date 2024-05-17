package com.oormthonunivswu.aboutme.Controller;

import com.oormthonunivswu.aboutme.Dto.JoinRequestDto;
import com.oormthonunivswu.aboutme.Dto.LoginRequestDto;
import com.oormthonunivswu.aboutme.Entity.User;
import com.oormthonunivswu.aboutme.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping("/api/join")
    public String join(@RequestBody JoinRequestDto joinRequestDto) {

        return userService.join(joinRequestDto);
    }

    @PostMapping("/api/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }


    @GetMapping("/api/info")
    public Map<String, Object> info(Principal principal) {
        return userService.getInfo(principal);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }
}
