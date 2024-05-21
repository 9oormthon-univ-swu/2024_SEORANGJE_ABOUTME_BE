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

import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;

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

    private static final String[] FOLDER_IMAGE_URLS = {
            "https://2024aboutme.s3.ap-northeast-2.amazonaws.com/folder_%ED%8F%B4%EB%8D%94.png",
            "https://2024aboutme.s3.ap-northeast-2.amazonaws.com/folder_%EC%9D%8C%EB%B0%98.png",
            "https://2024aboutme.s3.ap-northeast-2.amazonaws.com/folder_%EC%BB%B4%ED%93%A8%ED%84%B0.png",
            "https://2024aboutme.s3.ap-northeast-2.amazonaws.com/folder_%ED%8E%B8%EC%A7%80%EB%B4%89%ED%88%AC.png",
            "https://2024aboutme.s3.ap-northeast-2.amazonaws.com/folder_%ED%8E%B8%EC%A7%80%EB%B4%89%ED%88%AC.png",
            "https://2024aboutme.s3.ap-northeast-2.amazonaws.com/folder_%ED%95%98%ED%8A%B8+%ED%8E%B8%EC%A7%80%EC%A7%80.png"
    };

    public List<MyImageDTO> getAllMyImagesByUserId(User user_id){
        List<MyImageEntity> myImageEntities = myImageRepository.findByUserId(user_id);
        // myImageEntities가 null이면 이미지가 생성되지 않았음을 프론트엔드로 알려줍니다.
        if (myImageEntities == null || myImageEntities.isEmpty()) {
            return Collections.singletonList(new MyImageDTO("사용자의 게시판에 마이이미지가 생성되지 않았습니다."));
        }

        // 그 외의 경우에는 이미지 목록을 반환합니다.
        return myImageEntities.stream()
                .map(image-> new MyImageDTO(image.getId(), image.getGuestNickname(),image.getFolderImageUrl()))
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

        String randomFolderImageUrl = getRandomForderImageUrl();


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
                .folderImageUrl(randomFolderImageUrl)//랜덤 폴더 이미지 url 추가
                .build();

        myImageRepository.save(myImageEntity);
    }

    private String getRandomForderImageUrl(){
        Random random = new Random();
        int index = random.nextInt(FOLDER_IMAGE_URLS.length);
        return FOLDER_IMAGE_URLS[index];
    }

}
