package com.oormthonunivswu.aboutme.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class S3Service {
    @Value("${spring.cloud.aws.s3.bucket}")
    public String bucketName;
    @Value("${spring.cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${spring.cloud.aws.credentials.secretKey}")
    private String secretKey;

//    private final AmazonS3 amazonS3;
//
//    public S3Service() {
//        this.amazonS3 = AmazonS3ClientBuilder.standard().build();
//    }
    public String uploadFile(MultipartFile file) throws IOException {
        // AWS 인증 정보 생성
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);

        // AWS S3 클라이언트 생성
        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.AP_NORTHEAST_2)
                .withCredentials(credentialsProvider)
                .build();

        // 파일 이름 생성
        String fileName = generateFileName(file.getOriginalFilename());

        // 파일 업로드
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        s3Client.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata));

        // 업로드된 파일의 URL 반환
        return s3Client.getUrl(bucketName, fileName).toString();
    }

    // 파일 이름 생성 메서드 (중복 방지를 위해 UUID 사용)
    private String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "_" + originalFileName;
    }
}