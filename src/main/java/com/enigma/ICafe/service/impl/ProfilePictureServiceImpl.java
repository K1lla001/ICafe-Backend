package com.enigma.ICafe.service.impl;

import com.enigma.ICafe.entity.BaseFile;
import com.enigma.ICafe.entity.ProfilePicture;
import com.enigma.ICafe.entity.UserCredential;
import com.enigma.ICafe.repository.ProfilePictureRepository;
import com.enigma.ICafe.security.UserSecurity;
import com.enigma.ICafe.service.BaseFileService;
import com.enigma.ICafe.service.ProfilePictureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfilePictureServiceImpl implements ProfilePictureService {

    private final ProfilePictureRepository profilePictureRepository;
    private final BaseFileService baseFileService;
    private final UserSecurity userSecurity;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProfilePicture create(UserCredential userCredential, MultipartFile multipartFile) {
        try {
            log.warn("ADD PROFILE PICTURE START");
            ProfilePicture picture = ProfilePicture.builder()
                    .user(userCredential)
                    .build();
            log.warn(picture.getUser().toString());
            profilePictureRepository.saveAndFlush(picture);
            BaseFile baseFile = baseFileService.create(multipartFile);
            picture.setName(baseFile.getName());
            picture.setSize(baseFile.getSize());
            picture.setContentType(baseFile.getContentType());
            picture.setPath(baseFile.getPath());
            log.info("ADD PROFILE PICTURE END");
            return picture;
        }catch (Exception e){
            log.warn("ERROR: CREATING PROFILE PICTURE : {}" , e.getMessage(), e.getCause());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "You're Already Have Profile Picture!");
        }


    }

    @Override
    public Resource download(String id) {
        ProfilePicture profilePicture = profilePictureRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image Not Found!"));
        return baseFileService.get(profilePicture.getPath());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteById(String id) {
        ProfilePicture profilePicture = profilePictureRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found!"));
        userSecurity.validateUserByEmail(profilePicture.getUser().getEmail());
        String path = profilePicture.getPath();
        profilePictureRepository.delete(profilePicture);
    }
}
