package com.enigma.ICafe.service;

import com.enigma.ICafe.entity.Admin;
import com.enigma.ICafe.model.request.UpdateAdminRequest;
import com.enigma.ICafe.model.response.AdminResponse;
import org.springframework.security.core.Authentication;

public interface AdminService {

    AdminResponse authenticateUser(Authentication authentication);
    Admin create(Admin admin);

    AdminResponse update(UpdateAdminRequest request);

    Admin findById(String id);
}
