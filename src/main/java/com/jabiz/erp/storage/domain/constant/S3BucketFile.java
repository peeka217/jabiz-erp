package com.jabiz.erp.storage.domain.constant;

import lombok.Builder;
import lombok.Data;

@Data
public class S3BucketFile {

    private String uploadedFileURL;
    private String uploadedFileKey;

    @Builder
    public S3BucketFile(String uploadedFileURL, String uploadedFileKey) {
        this.uploadedFileURL = uploadedFileURL;
        this.uploadedFileKey = uploadedFileKey;
    }

}
