package com.oormthonunivswu.aboutme.Controller;


import com.oormthonunivswu.aboutme.Dto.DefaultImageDTO;
import com.oormthonunivswu.aboutme.Dto.MyImageDTO;
import com.oormthonunivswu.aboutme.Dto.MyImageDetailDTO;
import com.oormthonunivswu.aboutme.Dto.MyImageRequestDTO;
import com.oormthonunivswu.aboutme.Entity.DefaultImageEntity;
import com.oormthonunivswu.aboutme.Entity.ImageEntity;
import com.oormthonunivswu.aboutme.Entity.MyImageEntity;
import com.oormthonunivswu.aboutme.Entity.User;
import com.oormthonunivswu.aboutme.Repository.ImageRepository;
import com.oormthonunivswu.aboutme.Repository.MyImageRepository;
import com.oormthonunivswu.aboutme.Repository.UserRepository;
import com.oormthonunivswu.aboutme.Service.DefaultImageService;
import com.oormthonunivswu.aboutme.Service.MyImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)

@RestController
@RequestMapping("/api/MyImage")
public class MyImageController {

    @Autowired
    private MyImageService myImageService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DefaultImageService defaultImageService;
    @Autowired
    private MyImageRepository myImageRepository;
    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/List/{user_id}")
    public List<MyImageDTO> getAllMyImagesByUserId(@PathVariable User user_id){
        return myImageService.getAllMyImagesByUserId(user_id);
    }
    public List<DefaultImageDTO> getDefaultImages(@PathVariable Long user_id){
        User user = userRepository.findById(user_id).orElseThrow(()-> new RuntimeException("사용자를 찾을 수 없습니다"));
        List<DefaultImageEntity> defaultImageEntities = defaultImageService.getDefaultImagesByUserCategory(user);
        return defaultImageEntities.stream()
                .map(image -> new DefaultImageDTO(image.getId(), image.getCategory(), image.getImageName(), image.getImageDetail(), image.getFilePath()))
                .collect(Collectors.toList());

    }

    @PostMapping("/{userId}")
    public String createMyImage(@PathVariable Long userId, @ModelAttribute MyImageRequestDTO requestDTO) {
        try {
            myImageService.createMyImage(userId, requestDTO);
            return "My image created successfully";
        } catch (IOException e) {
            return "Failed to create my image: " + e.getMessage();
        }
    }
    @GetMapping("/{myimage_id}")
    public MyImageDetailDTO getMyImageDetail(@PathVariable("myimage_id") Long myimageId) {
        MyImageEntity myImageEntity = myImageRepository.findById(myimageId)
                .orElseThrow(() -> new RuntimeException("마이이미지를 찾을 수 없습니다: " + myimageId));

        Map<String, String> imageFilePaths = new HashMap<>();
        Map<String, String> imageFileName = new HashMap<>();

        // 주제 9개 컬럼 중에서 null이 아닌 값을 가진 주제들을 찾아서 해당 주제의 이름 반환
        addImageFileNameNPath("animal", "떠오르는 동물", myImageEntity.getAnimal(), imageFileName, imageFilePaths);
        addImageFileNameNPath("charac", "닮은 캐릭터", myImageEntity.getCharac(), imageFileName, imageFilePaths);
        addImageFileNameNPath("color", "어울리는 색깔", myImageEntity.getColor(), imageFileName, imageFilePaths);
        addImageFileNameNPath("flower", "어울리는 꽃", myImageEntity.getFlower(), imageFileName, imageFilePaths);
        addImageFileNameNPath("food", "생각나는 음식", myImageEntity.getFood(), imageFileName, imageFilePaths);
        addImageFileNameNPath("hobby", "어울리는 취미", myImageEntity.getHobby(), imageFileName, imageFilePaths);
        addImageFileNameNPath("job", "어울리는 직업", myImageEntity.getJob(), imageFileName, imageFilePaths);
        addImageFileNameNPath("place", "어울리는 장소", myImageEntity.getPlace(), imageFileName, imageFilePaths);
        addImageFileNameNPath("season", "어울리는 계절", myImageEntity.getSeason(), imageFileName, imageFilePaths);

        return new MyImageDetailDTO(
                myImageEntity.getGuestNickname(),
                myImageEntity.getImageComment(),
                imageFileName,
                imageFilePaths

        );
    }

    private void addImageFileNameNPath(String subject, String imageName, ImageEntity imageEntity,
                                  Map<String, String> imageFileName, Map<String, String> imageFilePaths) {
        if (imageEntity != null) {
            ImageEntity foundImageEntity = imageRepository.findById(imageEntity.getId())
                    .orElseThrow(() -> new RuntimeException("이미지를 찾을 수 없습니다: " + imageEntity.getId()));
            imageFileName.put(subject, imageName);
            imageFilePaths.put(subject, foundImageEntity.getFilePath());
        }
    }
}