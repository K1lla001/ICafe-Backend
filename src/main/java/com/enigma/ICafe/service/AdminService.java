package com.enigma.ICafe.service;

import com.enigma.ICafe.dto.request.AuthRequest;
import com.enigma.ICafe.dto.response.LoginResponse;
import com.enigma.ICafe.dto.response.RegisterResponse;
import com.enigma.ICafe.entity.Admin;
import com.enigma.ICafe.entity.Role;
import com.enigma.ICafe.entity.UserCredential;

public interface AdminService {

    Admin create(Admin admin);

    Admin update(Admin admin);

    String delete(String id);

}
