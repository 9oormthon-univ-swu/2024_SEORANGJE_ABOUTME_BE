package com.oormthonunivswu.aboutme.Service;

import com.oormthonunivswu.aboutme.Dto.JoinRequestDto;
import com.oormthonunivswu.aboutme.Dto.LoginRequestDto;
import com.oormthonunivswu.aboutme.Entity.User;
import com.oormthonunivswu.aboutme.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public String join(JoinRequestDto joinRequestDto) {
        // 클라이언트로부터 받은 회원가입 정보
        String email = joinRequestDto.getEmail();
        String password = joinRequestDto.getPassword();
        String name = joinRequestDto.getUsername();
        // (필요한 경우 추가 정보도 받아올 수 있음)

        // 이메일이 이미 존재하는지 확인
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            return "해당 이메일은 이미 등록되어 있습니다.";
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        // 새로운 사용자 생성
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);
        newUser.setUsername(name);
        // (필요한 경우 추가 정보도 설정 가능)

        // 사용자 저장
        userRepository.save(newUser);

        return "회원가입 성공";
    }

    public String login(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();
        String rawPassword = loginRequestDto.getPassword();

        User byEmail = userRepository.findByEmail(email);

        // 비밀번호 일치 여부 확인
        if(passwordEncoder.matches(rawPassword, byEmail.getPassword())){
            return "로그인 성공";
        }

        return "로그인 실패";
    }
}
