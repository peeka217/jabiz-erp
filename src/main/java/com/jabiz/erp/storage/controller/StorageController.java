package com.jabiz.erp.storage.controller;

import com.jabiz.erp.storage.infra.S3BucketAgency;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/storage")
public class StorageController {

    private final S3BucketAgency s3BucketAgency;

    private HttpHeaders buildHeaders(String resourcePath, byte[] data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(data.length);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(CommonUtils.createContentDisposition(resourcePath));
        return headers;
    }
//    @GetMapping("/images")
//    public ResponseEntity<ByteArrayResource> test(@RequestPart(value = "images", required = false) List<MultipartFile> multipartFiles)
//            throws IOException {
//
//
////        List<S3BucketFile> s3BucketFiles = new ArrayList<>();
////        multipartFiles.forEach(multipartFile -> {
////            try {
////                s3BucketFiles.add(s3BucketAgency.uploadToS3(multipartFile, "erp/image/worker/identification"));
////            } catch (IOException e) {
////                throw new RuntimeException();
////            }
////        });
////
////        return null;
//
////        String key = "erp/image/worker/identification/6ed21c7d-936d-4d7e-bf29-9aec055ca455testestest.png";
////        byte[] data = s3BucketAgency.download(key);
////        ByteArrayResource resource = new ByteArrayResource(data);
////        HttpHeaders headers = buildHeaders(key, data);
////        return ResponseEntity
////                .ok()
////                .headers(headers)
////                .body(resource);
//
//    }
}
