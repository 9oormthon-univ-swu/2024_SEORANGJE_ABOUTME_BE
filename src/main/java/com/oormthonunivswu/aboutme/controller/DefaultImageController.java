package com.oormthonunivswu.aboutme.Controller;

import com.oormthonunivswu.aboutme.Dto.DefaultImageDTO;
import com.oormthonunivswu.aboutme.Dto.UserDefaultImageDTO;
import com.oormthonunivswu.aboutme.Entity.DefaultImageEntity;
import com.oormthonunivswu.aboutme.Entity.User;
import com.oormthonunivswu.aboutme.Repository.UserRepository;
import com.oormthonunivswu.aboutme.Service.DefaultImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/DefaultImage")
public class DefaultImageController {
    @Autowired
    private DefaultImageService defaultImageService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getDefaultImages(@PathVariable Long user_id) {
        try {
            User user = userRepository.findById(user_id)
                    .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다"));
            List<DefaultImageEntity> defaultImageEntities = defaultImageService.getDefaultImagesByUserCategory(user);

            if (defaultImageEntities.isEmpty()) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "저장된 디폴트 이미지가 없습니다.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            List<DefaultImageDTO> defaultImageDTOS = defaultImageEntities.stream()
                    .map(image -> new DefaultImageDTO(image.getId(), image.getCategory(), image.getImageName(), image.getImageDetail(), image.getFilePath()))
                    .collect(Collectors.toList());

            UserDefaultImageDTO userDefaultImageDTO = new UserDefaultImageDTO(user.getId(),user.getUsername(),defaultImageDTOS);

            return ResponseEntity.ok(userDefaultImageDTO);

        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
