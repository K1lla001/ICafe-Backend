package com.enigma.ICafe.service;

import com.enigma.ICafe.entity.BaseFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.List;

public interface BaseFileService {

    BaseFile create(MultipartFile multipartFile);
    List<File> createBulk(List<MultipartFile> multipartFiles);
    Resource get(String path);
    String delete(String path);


}
