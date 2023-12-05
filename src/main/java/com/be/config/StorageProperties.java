package com.be.config;

import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("storage")
@Data
@NoArgsConstructor
public class StorageProperties {
	private String location;
}


