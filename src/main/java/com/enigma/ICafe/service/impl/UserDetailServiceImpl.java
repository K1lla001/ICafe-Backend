package com.enigma.ICafe.service.impl;

import com.enigma.ICafe.entity.Role;
import com.enigma.ICafe.entity.UserCredential;
import com.enigma.ICafe.entity.UserDetailsImpl;
import com.enigma.ICafe.repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserCredentialRepository userCredentialRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User With Email %s Not Found", email)));

        Role role = userCredential.getRole();
        Collection<SimpleGrantedAuthority> grantedAuthorities =
                Collections.singleton(new SimpleGrantedAuthority(role.getRole().name()));

        return UserDetailsImpl.builder()
                .email(userCredential.getEmail())
                .password(userCredential.getPassword())
                .authorities(grantedAuthorities)
                .build();
    }
}
