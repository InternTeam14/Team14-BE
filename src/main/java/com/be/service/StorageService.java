package com.be.service;



import java.io.IOException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	String getStoreFilename(MultipartFile file, String id);

	void store(MultipartFile file, String storeFilename);

	Resource loadAsResource(String filename);

	Path load(String filename);

	void delete(String storedFilename) throws IOException;

	void init();

	
}
