package com.quanlyphongkhamvadatlich.service.client;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
    public void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException;
    public void delete(String fileLocation);
}
