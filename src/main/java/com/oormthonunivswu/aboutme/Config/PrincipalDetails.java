package com.oormthonunivswu.aboutme.Config;

import com.oormthonunivswu.aboutme.Entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * UserDetails를 이용해서 User객체에 대한 정보를 검색한다.
 * UserDetailsService는 인터페이스이므로, 우리가 인증하고자하는 비즈니스로직을 정의한 serivce레이어에서 구현을 실행하는 방식을 이용한다.
 */
@Data
@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        user.getRolesList().forEach(r -> {
//            authorities.add(() -> r);
//        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
