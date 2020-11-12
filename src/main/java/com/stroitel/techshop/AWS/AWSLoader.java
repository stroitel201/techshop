package com.stroitel.techshop.AWS;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public String UploadObject(MultipartFile targetFile){
        try {
            // Upload a file as a new object with ContentType and title specified.
            Path file = Paths.get(targetFile.toURI());
            String key = file.getFileName().toString();
            String type = Files.probeContentType(file);
            s3Client.putObject(PutObjectRequest.builder().bucket(bucketName).
                    key(key).
                    acl(ObjectCannedACL.PUBLIC_READ).contentType(type).build(),
                    RequestBody.fromFile(file));
            GetUrlRequest request = GetUrlRequest.builder().bucket(bucketName).key(key).build();
            return s3Client.utilities().getUrl(request).toExternalForm();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
