package com.oormthonunivswu.aboutme.MyImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyImageService {

    @Autowired
    private MyImageRepository myImageRepository;
}
