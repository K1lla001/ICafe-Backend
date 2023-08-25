package com.enigma.ICafe.service.impl;

import com.enigma.ICafe.entity.Admin;
import com.enigma.ICafe.entity.UserDetailsImpl;
import com.enigma.ICafe.model.request.UpdateAdminRequest;
import com.enigma.ICafe.model.response.AdminResponse;
import com.enigma.ICafe.repository.AdminRepository;
import com.enigma.ICafe.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    @Override
    public AdminResponse authenticateUser(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = adminRepository.findFirstByUserCredential_Email(userDetails.getEmail()).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin Not Found!"));
        return generateResponse(admin);
    }
    @Override
    public Admin create(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public AdminResponse update(UpdateAdminRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AdminResponse adminResponse = authenticateUser(authentication);
        if (adminResponse.getAdminId().equals(request.getAdminId())) {
            Admin admin = findById(request.getAdminId());
            admin.setFullName(request.getFullName());
            admin.setPhoneNumber(request.getPhoneNumber());
            adminRepository.save(admin);
            return generateResponse(admin);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to access this resource!");
    }
    @Override
    public Admin findById(String id) {
        return adminRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }
    private AdminResponse generateResponse(Admin admin) {
        return AdminResponse.builder()
                .adminId(admin.getId())
                .email(admin.getEmail())
                .fullName(admin.getFullName())
                .phoneNumber(admin.getPhoneNumber())
                .build();
    }


}
