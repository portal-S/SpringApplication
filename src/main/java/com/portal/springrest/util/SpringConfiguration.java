package com.portal.springrest.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
public class SpringConfiguration {

    @Bean
    public AmazonS3 getAmazon(){
        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAXPDQVNVGYP5A2IM6",
                "i2uUBTqjNV+dfKjndxEhi9Dka0Y4kHDhhqJSX/JI"
        );
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
        return s3client;

    }
}
