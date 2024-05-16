package com.oormthonunivswu.aboutme.Service;

import com.oormthonunivswu.aboutme.Config.JwtProvider;
import com.oormthonunivswu.aboutme.Dto.JoinRequestDto;
import com.oormthonunivswu.aboutme.Dto.LoginRequestDto;
import com.oormthonunivswu.aboutme.Entity.User;
import com.oormthonunivswu.aboutme.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;

    public String join(JoinRequestDto joinRequestDto) {
        // 클라이언트로부터 받은 회원가입 정보
        String email = joinRequestDto.getEmail();
        String password = joinRequestDto.getPassword();
        String name = joinRequestDto.getUsername();
        boolean flower = joinRequestDto.getFlower();
        boolean animal = joinRequestDto.getAnimal();
        boolean season = joinRequestDto.getSeason();
        boolean color = joinRequestDto.getColor();
        boolean charac = joinRequestDto.getCharac();
        boolean place = joinRequestDto.getPlace();
        boolean food = joinRequestDto.getFood();
        boolean hobby = joinRequestDto.getHobby();
        boolean job = joinRequestDto.getJob();


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
        newUser.setFlower(flower);
        newUser.setAnimal(animal);
        newUser.setSeason(season);
        newUser.setColor(color);
        newUser.setCharac(charac);
        newUser.setPlace(place);
        newUser.setFood(food);
        newUser.setHobby(hobby);
        newUser.setJob(job);

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

            // JWT 토큰 반환
            String jwtToken = jwtProvider.generateJwtToken(byEmail.getId(), byEmail.getEmail(), byEmail.getUsername());

            return "로그인 성공 " + jwtToken;
        }

        return "로그인 실패";
    }


    public User getUserById(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }
}
