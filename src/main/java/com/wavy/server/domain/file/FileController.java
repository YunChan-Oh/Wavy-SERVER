package com.wavy.server.domain.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/images")
public class FileController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ImageResponse upload(MultipartFile file) {

            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

            String uniqueFileName = UUID.randomUUID() + fileExtension;

            Path targetLocation = Paths.get(uploadDir).resolve(uniqueFileName);

            try {
                Files.createDirectories(targetLocation.getParent());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                Files.copy(file.getInputStream(), targetLocation);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return new ImageResponse(targetLocation.toString());

    }
}