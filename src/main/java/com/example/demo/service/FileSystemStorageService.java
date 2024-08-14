package com.example.demo.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@Slf4j
public class FileSystemStorageService {
    private final Path rootLocation;

    // Use @Value to inject the property from application.properties
    public FileSystemStorageService(@Value("${storage.location}") String storageLocation) {
        this.rootLocation = Paths.get(storageLocation).toAbsolutePath().normalize();
    }

    public String store(MultipartFile file) throws IOException {
        String filename = Objects.requireNonNull(file.getOriginalFilename());
        Path destinationFile = this.rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
        log.info("Destination file path: {}", destinationFile);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            log.info("File saved successfully: {}", filename);
        } catch (IOException e) {
            log.error("Failed to save file: {}", e.getMessage());
            throw e;
        }
        return filename;
    }

    public void delete(String filename) throws IOException {
        Path filePath = this.rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
        log.info("Deleting file: {}", filePath);

        try {
            Files.deleteIfExists(filePath);
            log.info("File deleted successfully: {}", filename);
        } catch (IOException e) {
            log.error("Failed to delete file: {}", e.getMessage());
            throw e;
        }
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
