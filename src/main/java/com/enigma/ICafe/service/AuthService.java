package com.enigma.ICafe.service;

import com.enigma.ICafe.model.request.AuthRequest;
import com.enigma.ICafe.model.response.LoginResponse;
import com.enigma.ICafe.model.response.RegisterResponse;
import com.enigma.ICafe.entity.Role;
import com.enigma.ICafe.entity.UserCredential;

public interface AuthService {

    RegisterResponse registerAdmin(AuthRequest request);

    RegisterResponse registerCustomer(AuthRequest request);

    LoginResponse login(AuthRequest request);

    UserCredential createUserCredential(AuthRequest request, Role role);


}
