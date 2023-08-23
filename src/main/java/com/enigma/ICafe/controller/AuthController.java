package com.enigma.ICafe.controller;

import com.enigma.ICafe.dto.common.CommonResponse;
import com.enigma.ICafe.dto.request.AuthRequest;
import com.enigma.ICafe.dto.response.LoginResponse;
import com.enigma.ICafe.dto.response.RegisterResponse;
import com.enigma.ICafe.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<CommonResponse<RegisterResponse>> registerAdmin(@RequestBody AuthRequest request){
        RegisterResponse registerResponse = authService.registerAdmin(request);

        return ResponseEntity.ok(CommonResponse.<RegisterResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Create Admin!")
                        .data(registerResponse)
                .build());
    }

    @PostMapping(path = "/login")
    public ResponseEntity<CommonResponse<LoginResponse>> login(@RequestBody AuthRequest request){
        LoginResponse loggedAdmin = authService.login(request);
        return ResponseEntity.ok(CommonResponse.<LoginResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Login!")
                        .data(loggedAdmin)
                .build());
    }

}
