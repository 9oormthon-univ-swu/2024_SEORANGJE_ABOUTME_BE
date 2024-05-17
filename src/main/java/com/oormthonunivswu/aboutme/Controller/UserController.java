package com.oormthonunivswu.aboutme.Controller;

import com.oormthonunivswu.aboutme.Config.PrincipalDetails;
import com.oormthonunivswu.aboutme.Dto.JoinRequestDto;
import com.oormthonunivswu.aboutme.Dto.LoginRequestDto;
import com.oormthonunivswu.aboutme.Entity.User;
import com.oormthonunivswu.aboutme.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/api/url")
    public String info(@AuthenticationPrincipal PrincipalDetails principalDetails, Authentication authentication) {
        System.out.println("PrincipalDetails " + principalDetails);
        System.out.println("authentication " + authentication);

        StringBuilder sb = new StringBuilder();
        sb.append("PrincipalDetails ");
        sb.append(principalDetails);
        sb.append("\n\n");
        sb.append("authentication ");
        sb.append(authentication);

        return sb.toString();

    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }
}
