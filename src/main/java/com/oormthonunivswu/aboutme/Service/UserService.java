package com.oormthonunivswu.aboutme.Service;

import com.oormthonunivswu.aboutme.Config.JwtProvider;
import com.oormthonunivswu.aboutme.Config.PrincipalDetails;
import com.oormthonunivswu.aboutme.Dto.JoinRequestDto;
import com.oormthonunivswu.aboutme.Dto.LoginRequestDto;
import com.oormthonunivswu.aboutme.Entity.User;
import com.oormthonunivswu.aboutme.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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

        // 새로운 UUID 생성
        UUID uuid = UUID.randomUUID();
        newUser.setUuid(uuid);

        // 사용자 저장
        User savedUser = userRepository.save(newUser);

        // URL 생성 및 설정
        String url = "https://port-0-seorangje-aboutme-be-2024-1ru12mlwc1mxvw.sel5.cloudtype.app/" + uuid;
        savedUser.setUrl(url);


        userRepository.save(savedUser);

        return "회원가입 성공";
    }

    public String login(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();
        String rawPassword = loginRequestDto.getPassword();

        User byEmail = userRepository.findByEmail(email);

        // 사용자가 존재하지 않을 때
        if (byEmail == null) {
            return "로그인 실패 - 사용자가 존재하지 않습니다.";
        }

        // 비밀번호 일치 여부 확인
        if (passwordEncoder.matches(rawPassword, byEmail.getPassword())) {
            // JWT 토큰 반환
            String jwtToken = jwtProvider.generateJwtToken(byEmail.getId(), byEmail.getEmail(), byEmail.getUsername());
            return "로그인 성공 " + jwtToken;
        }

        // 비밀번호가 일치하지 않을 때
        return "로그인 실패 - 비밀번호가 일치하지 않습니다.";
    }

    public Map<String, Object> getInfo(Principal principal) {
        Map<String, Object> response = new HashMap<>();
        response.put("principalDetails", principal);
        response.put("authentication", ((Authentication) principal).getAuthorities()); // 권한 정보도 추가할 수 있습니다.

        return response;
    }
    public Map<String, Object> getUrl(Principal principal) {
        Map<String, Object> response = new HashMap<>();

        if (principal != null) {
            String url = null;
            Object principalObject = ((Authentication) principal).getPrincipal();
            if (principalObject instanceof PrincipalDetails) {
                url = ((PrincipalDetails) principalObject).getUser().getUrl();
            }
            response.put("url", url);
        } else {
            response.put("url", null);
        }

        return response;
    }

    public User getUserByUuid(UUID uuid) {
        Optional<User> userOptional = userRepository.findByUuid(uuid);
        return userOptional.orElse(null);

    }
    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }
}
