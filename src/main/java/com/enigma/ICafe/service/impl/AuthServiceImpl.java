package com.enigma.ICafe.service.impl;

import com.enigma.ICafe.dto.request.AuthRequest;
import com.enigma.ICafe.dto.response.LoginResponse;
import com.enigma.ICafe.dto.response.RegisterResponse;
import com.enigma.ICafe.entity.Admin;
import com.enigma.ICafe.entity.Role;
import com.enigma.ICafe.entity.UserCredential;
import com.enigma.ICafe.entity.UserDetailsImpl;
import com.enigma.ICafe.entity.constant.ERole;
import com.enigma.ICafe.repository.UserCredentialRepository;
import com.enigma.ICafe.security.JwtSecurityConfig;
import com.enigma.ICafe.service.AdminService;
import com.enigma.ICafe.service.AuthService;
import com.enigma.ICafe.service.RoleService;
import com.enigma.ICafe.utils.BcryptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class AuthServiceImpl implements AuthService {

    private final UserCredentialRepository userCredentialRepository;
    private final BcryptUtil bcryptUtil;
    private final AdminService adminService;
    private final RoleService roleService;
    private final JwtSecurityConfig jwtSecurityConfig;
    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterResponse registerAdmin(AuthRequest request) {
        try {
            Role admin = roleService.getOrSave(ERole.ROLE_ADMIN);
            UserCredential userCredential = createUserCredential(request, admin);

            Admin currentAdmin = Admin.builder()
                    .email(request.getEmail())
                    .fullName(defaultNameGenerator(request.getEmail()))
                    .userCredential(userCredential)
                    .build();

            adminService.create(currentAdmin);
            return RegisterResponse.builder().email(currentAdmin.getEmail()).build();
        }catch (DataIntegrityViolationException exception){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User Already Exist!");
        }
    }
    @Override
    public LoginResponse login(AuthRequest request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetailsImpl user = (UserDetailsImpl) authenticate.getPrincipal();
        List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        String currentRole = "";
        if(!roles.isEmpty()) currentRole = roles.get(0);

        String jwtToken = jwtSecurityConfig.generateToken(user.getEmail());
        return LoginResponse.builder()
                .email(user.getEmail())
                .role(currentRole)
                .token(jwtToken)
                .build();
    }

    @Override
    public UserCredential createUserCredential(AuthRequest request, Role role) {
        return userCredentialRepository.saveAndFlush(
                UserCredential.builder()
                        .email(request.getEmail())
                        .password(bcryptUtil.hashPassword(request.getPassword()))
                        .role(role)
                        .build()
        );
    }

    private String defaultNameGenerator(String fullName){
        int currentAt = fullName.indexOf("@");
        if(currentAt != -1){
            return fullName.substring(0, currentAt);
        }
        return fullName;
    }
}
