package com.enigma.ICafe.service;

import com.enigma.ICafe.entity.ProfilePicture;
import com.enigma.ICafe.entity.UserCredential;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

public interface ProfilePictureService {
    ProfilePicture create(UserCredential userCredential, MultipartFile multipartFile);
    Resource download(String id);
    String deleteById(String id);


}
