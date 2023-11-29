package com.be.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.be.config.StorageProperties;
import com.be.service.StorageService;
import com.be.utils.StorageException;
import com.be.utils.StorageFileException;



@Service
public class FileSystemStorageServiceImpl implements StorageService {

	private final Path rootLocation;

	@Override
	public String getStoredFileName(MultipartFile file, String id) {
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		return "p" + id + "." + ext;
	}


	public FileSystemStorageServiceImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file, String storedFilename) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Fail not empty file");
			}
			Path destinationfile = this.rootLocation.resolve(Paths.get(storedFilename)).normalize().toAbsolutePath();
			
			if (!destinationfile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				throw new StorageException("Cann't store outside current directory");
			}
			
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationfile, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			throw new StorageException("Faild to store file", e);

		}
	}

	@Override
	public Resource loadAsResource(String fileName) {
		try {
			Path file = load(fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			throw new StorageFileException("Cann't read file: " + fileName);
		} catch (Exception e) {
			throw new StorageFileException("Cann't read file: " + fileName);
		}
	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public void delete(String storedFilename) throws IOException {
		Path destinationfile = this.rootLocation.resolve(Paths.get(storedFilename)).normalize().toAbsolutePath();
		Files.delete(destinationfile);
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
			System.out.println(rootLocation.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
