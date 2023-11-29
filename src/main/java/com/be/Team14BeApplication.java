package com.be;

import com.be.config.StorageProperties;
import com.be.service.impl.FileSystemStorageServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class Team14BeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Team14BeApplication.class, args);
    }

}
