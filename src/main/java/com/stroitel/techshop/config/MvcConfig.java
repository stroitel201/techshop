package com.stroitel.techshop.config;


import com.stroitel.techshop.AWS.AWSLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import software.amazon.awssdk.regions.Region;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${aws.accessKeyId}")
    String keyId;
    @Value("${aws.secretAccessKey}")
    String secretKey;
    @Value("${aws.bucketName}")
    String bucketName;
    @Value("${aws.region}")
    String region;

    @Bean
    public AWSLoader awsLoader(){

        System.setProperty("aws.accessKeyId", keyId);
        System.setProperty("aws.secretAccessKey", secretKey);

        return new AWSLoader(Region.of(region), bucketName);
    }
}
