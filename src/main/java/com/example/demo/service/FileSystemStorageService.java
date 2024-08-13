package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileSystemStorageService {
    private final Path rootLocation;

    // Use @Value to inject the property from application.properties
    public FileSystemStorageService(@Value("${storage.location}") String storageLocation) {
        this.rootLocation = Paths.get(storageLocation).toAbsolutePath().normalize();
    }

    public String store(MultipartFile file) throws IOException {
        Path destinationFile = this.rootLocation.resolve(Paths.get(Objects.requireNonNull(file.getOriginalFilename()))).normalize().toAbsolutePath();
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        }
        return file.getOriginalFilename(); // Return the filename
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
