package com.oormthonunivswu.aboutme.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oormthonunivswu.aboutme.Entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    // 인증 객체(Authentication)을 만들기 시도
    // attemptAuthentication 추상메소드의 구현은 상속한 UsernamePasswordAuthenticationFilter에 구현 되어 있습니다.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword()
            );

            /*
            - Filter를 통해 AuthenticationToken을 AuthenticationManager에 위임한다.
               UsernamePasswordAuthenticationToken 오브젝트가 생성된 후, AuthenticationManager의 인증 메소드를 호출한다.
            - PrincipalDetailsService의 loadUserByUsername() 함수가 실행된다.
            => 정상이면 authentication이 반환된다.
            * */
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            /*
            - authentication 객체가 session 영역에 정보를 저장한다. -> 로그인 처리
            - authenticatino 객체가 세션에 저장한 '방식'을 반환한다.
            - 참고 : security가 권한을 관리해주기 때문에 굳이 JWT에서는 세션을 만들필요는 없지만, 권한 처리를 위해 session을 사용한다.
             */

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("Authentication 확인: "+principalDetails.getUser().getUsername());

            return  authentication;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // attemptAuthentication 메소드가 호출 된 후 동작
    // response에 JWT 토큰을 담아서 보내준다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        Long id = principalDetails.getUser().getId();
        String email = principalDetails.getUser().getEmail();
        String userName = principalDetails.getUser().getUsername();

        String jwtToken = jwtProvider.generateJwtToken(id, email, userName);

        response.addHeader("Authorization", "Bearer " + jwtToken);

    }
}
