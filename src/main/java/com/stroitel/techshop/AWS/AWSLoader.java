package com.stroitel.techshop.AWS;


import lombok.Data;

import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;


@Data
public class AWSLoader {

    Region clientRegion;
    String bucketName;

    S3Client s3Client;

    public AWSLoader(Region clientRegion, String bucketName) {
        this.clientRegion = clientRegion;
        this.bucketName = bucketName;
        this.s3Client = S3Client.builder().region(clientRegion)
                .credentialsProvider(SystemPropertyCredentialsProvider.create())
                .build();
    }

    public String UploadObject(MultipartFile file){
        try {
            String key = file.getOriginalFilename();
            String type = file.getContentType();
            s3Client.putObject(PutObjectRequest.builder().bucket(bucketName).
                    key(key).
                    acl(ObjectCannedACL.PUBLIC_READ).contentType(type).build(),
                    RequestBody.fromBytes(file.getBytes()));
            GetUrlRequest request = GetUrlRequest.builder().bucket(bucketName).key(key).build();
            return s3Client.utilities().getUrl(request).toExternalForm();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
