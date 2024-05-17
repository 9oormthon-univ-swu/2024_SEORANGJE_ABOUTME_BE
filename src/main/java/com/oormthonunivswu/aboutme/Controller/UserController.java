package com.oormthonunivswu.aboutme.Controller;

import com.oormthonunivswu.aboutme.Dto.JoinRequestDto;
import com.oormthonunivswu.aboutme.Dto.LoginRequestDto;
import com.oormthonunivswu.aboutme.Entity.User;
import com.oormthonunivswu.aboutme.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> join(@RequestBody JoinRequestDto joinRequestDto) {
        String response = userService.join(joinRequestDto);
        if (response.equals("회원가입 성공")) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "회원가입 성공"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", response));
        }
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto loginRequestDto) {
        String response = userService.login(loginRequestDto);
        if (response.startsWith("로그인 성공")) {
            String jwtToken = response.replace("로그인 성공 ", "");
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "로그인 성공", "token", jwtToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "로그인 실패"));
        }
    }


    @GetMapping("/api/info")
    public Map<String, Object> info(Principal principal) {
        return userService.getInfo(principal);
    }

    @GetMapping("/api/url")
    public Map<String, Object> url(Principal principal) {
        return userService.getUrl(principal);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }
}
