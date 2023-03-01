package com.jabiz.erp.storage.infra;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.jabiz.erp.storage.domain.constant.S3BucketFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3BucketAgency {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.private-bucket}")
    public String PRIVATE_BUCKET;
    @Value("${cloud.aws.s3.public-bucket}")
    public String PUBLIC_BUCKET;
    @Value("${storage.local.images}")
    public String STORAGE_LOCAL_IMAGES;

    public byte[] downloadPrifavteFile() {
        return null;
    }

    public S3BucketFile uploadPrivateFile() {
        return null;
    }

    public void deletePrivateFile() {

    }



    private byte[] download(String bucket, String key) {
//        byte[] data = amazonS3Client.download("");

        S3Object data = amazonS3Client.getObject(bucket, key);
        S3ObjectInputStream inputStream = data.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private S3BucketFile uploadToS3(String bucket, MultipartFile multipartFile, String path) throws IOException {
        File localFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException());

        String uploadedFileKey = path + "/" + localFile.getName();

        amazonS3Client.putObject(new PutObjectRequest(bucket, uploadedFileKey, localFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        String uploadedFileURL = amazonS3Client.getUrl(bucket, uploadedFileKey).toString();

        deleteLocalFile(localFile);

        return S3BucketFile.builder()
                .uploadedFileURL(uploadedFileURL)
                .uploadedFileKey(uploadedFileKey)
                .build();
    }

    private void deleteFromS3(String bucket, String fileName) {
        amazonS3Client.deleteObject(bucket, fileName);
    }

    private void deleteLocalFile(File targetFile) {

        if (targetFile.delete()) {
            return;
        }
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(System.getProperty("user.dir") + "/" + UUID.randomUUID() + file.getOriginalFilename());
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

}
