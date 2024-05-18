package com.oormthonunivswu.aboutme.service;

import com.oormthonunivswu.aboutme.entity.UserEntity;
import com.oormthonunivswu.aboutme.repository.UserRepository;
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
