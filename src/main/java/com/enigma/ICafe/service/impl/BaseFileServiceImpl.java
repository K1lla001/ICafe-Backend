package com.enigma.ICafe.service.impl;

import com.enigma.ICafe.entity.BaseFile;
import com.enigma.ICafe.service.BaseFileService;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class BaseFileServiceImpl implements BaseFileService {

    @Value("${icafe.image-path-url}")
    private String path;

    @Override
    public BaseFile create(MultipartFile multipartFile) {
        if(multipartFile.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File Can Not Be Empty!");

        if(!List.of("image/jpeg", "image/png").contains(multipartFile.getContentType())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid content type!");
        }
        try {
            Path directoryPath = Paths.get(path);
            Files.createDirectories(directoryPath);
            String fileName = String.format("%d_%s", System.currentTimeMillis(), multipartFile.getOriginalFilename());
            Path filePath = directoryPath.resolve(fileName);
            Files.copy(multipartFile.getInputStream(), filePath);

            return BaseFile.builder()
                    .name(fileName)
                    .path(filePath.toString())
                    .size(multipartFile.getSize())
                    .contentType(multipartFile.getContentType())
                    .build();

        }catch (IOException | RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error");
        }
    }

    @Override
    public List<File> createBulk(List<MultipartFile> multipartFiles) {
        return null;
    }

    @Override
    public Resource get(String path) {
        Path filePath = Paths.get(path);
        try {
            return new UrlResource(filePath.toUri());
        }catch (MalformedURLException exception){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error!");
        }
    }

    @Override
    public String delete(String path) {
        try {
            Path filePath = Paths.get(path);
            boolean exists = Files.deleteIfExists(filePath);
            if (!exists) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found!");
            return Boolean.toString(true);
        } catch (IOException | RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server Failed!");
        }
    }
}
