package com.be;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.be.config.StorageProperties;
import com.be.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Team14BeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Team14BeApplication.class, args);
    }
    @Bean
    CommandLineRunner init(StorageService storageServices) {
    	return (args -> {
    		storageServices.init();
    	});
    }
}
