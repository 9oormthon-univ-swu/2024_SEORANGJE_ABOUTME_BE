package com.oormthonunivswu.aboutme.Service;


import com.oormthonunivswu.aboutme.Dto.MyImageDTO;
import com.oormthonunivswu.aboutme.Dto.MyImageRequestDTO;
import com.oormthonunivswu.aboutme.Entity.ImageEntity;
import com.oormthonunivswu.aboutme.Entity.MyImageEntity;
import com.oormthonunivswu.aboutme.Entity.User;
import com.oormthonunivswu.aboutme.Repository.ImageRepository;
import com.oormthonunivswu.aboutme.Repository.MyImageRepository;
import com.oormthonunivswu.aboutme.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MyImageService {

    @Autowired
    private MyImageRepository myImageRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private S3Service s3Service;

    public List<MyImageDTO> getAllMyImagesByUserId(User user_id){
        List<MyImageEntity> myImageEntities = myImageRepository.findByUserId(user_id);
        // myImageEntities가 null이면 이미지가 생성되지 않았음을 프론트엔드로 알려줍니다.
        if (myImageEntities == null || myImageEntities.isEmpty()) {
            return Collections.singletonList(new MyImageDTO("사용자의 게시판에 마이이미지가 생성되지 않았습니다."));
        }

        // 그 외의 경우에는 이미지 목록을 반환합니다.
        return myImageEntities.stream()
                .map(image-> new MyImageDTO(image.getId(), image.getGuestNickname(),image.getImageComment()))
                .collect(Collectors.toList());
    }

    public void createMyImage(Long userId, MyImageRequestDTO requestDTO) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다"));

        Map<String, MultipartFile> imageFiles = requestDTO.getImageFiles();
        Map<String, String> imageUrls = requestDTO.getImageUrls();
        Map<String, ImageEntity> savedImages = new HashMap<>();

        // Null 체크 추가
        if (imageFiles != null) {
            // Process image files
            for (Map.Entry<String, MultipartFile> entry : imageFiles.entrySet()) {
                String imageUrl = s3Service.uploadFile(entry.getValue());
                ImageEntity imageEntity = ImageEntity.builder()
                        .originalFileName(entry.getValue().getOriginalFilename())
                        .serverFileName(imageUrl)
                        .category(entry.getKey())
                        .filePath(imageUrl)
                        .build();
                savedImages.put(entry.getKey(), imageRepository.save(imageEntity));
            }
        }

        // Null 체크 추가
        if (imageUrls != null) {
            // Process image URLs
            for (Map.Entry<String, String> entry : imageUrls.entrySet()) {
                ImageEntity imageEntity = imageRepository.findByFilePath(entry.getValue())
                        .orElseThrow(() -> new RuntimeException("Image not found"));

                savedImages.put(entry.getKey(), imageEntity);
            }
        }

        MyImageEntity myImageEntity = MyImageEntity.builder()
                .userId(user)
                .guestNickname(requestDTO.getGuestNickname())
                .imageComment(requestDTO.getImageComment())
                .flower(savedImages.get("flower"))
                .animal(savedImages.get("animal"))
                .season(savedImages.get("season"))
                .color(savedImages.get("color"))
                .charac(savedImages.get("charac"))
                .place(savedImages.get("place"))
                .food(savedImages.get("food"))
                .hobby(savedImages.get("hobby"))
                .job(savedImages.get("job"))
                .build();

        myImageRepository.save(myImageEntity);
    }

}
