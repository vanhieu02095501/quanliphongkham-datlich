package com.quanlyphongkhamvadatlich.service.doctor.impl;

import com.quanlyphongkhamvadatlich.service.doctor.IFileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageServiceImpl implements IFileStorageService {
//
//    private final Path fileStorageLocation;
//
//    public FileStorageServiceImpl(@Value("${file.upload-dir}") String uploadDir) {
//        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
//        try {
//            Files.createDirectories(this.fileStorageLocation);
//        } catch (Exception ex) {
//            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
//        }
//    }
//
//    @Override
//    public String storeFile(MultipartFile file) throws IOException {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        try {
//            if (fileName.contains("..")) {
//                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//
//            Path targetLocation = this.fileStorageLocation.resolve(fileName);
//            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//
//            return fileName;
//        } catch (IOException ex) {
//            throw new IOException("Could not store file " + fileName + ". Please try again!", ex);
//        }
//    }
//    @Override
//    public Path getFileStorageLocation() {
//        return this.fileStorageLocation;
//    }
//    @Override
//    public byte[] loadFileAsResource(String fileName) throws IOException {
//        try {
//            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
//            return Files.readAllBytes(filePath);
//        } catch (IOException ex) {
//            throw new IOException("Could not read file: " + fileName, ex);
//        }
//    }
//
//    @Override
//    public void deleteFile(String fileName) throws IOException {
//        try {
//            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
//            Files.delete(filePath);
//        } catch (IOException ex) {
//            throw new IOException("Could not delete file: " + fileName, ex);
//        }
//    }
}