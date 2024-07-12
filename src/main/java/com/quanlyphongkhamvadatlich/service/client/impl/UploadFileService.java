package com.quanlyphongkhamvadatlich.service.client.impl;

import java.io.*;
import java.nio.file.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.quanlyphongkhamvadatlich.service.client.IUploadFileService;

@Service
public class UploadFileService implements IUploadFileService {

    @Override
    public void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }     
    }

    @Override
    public void delete(String fileLocation) {
        try {
            Path fileDelete = Paths.get(fileLocation);
            Files.deleteIfExists(fileDelete);
          } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
          }
    }

}
