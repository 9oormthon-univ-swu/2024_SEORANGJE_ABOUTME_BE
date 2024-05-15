package com.oormthonunivswu.aboutme.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity findById(Long id){
        return userRepository.findById(id).orElse(null);
    }
}
