package com.enigma.ICafe.service.impl;

import com.enigma.ICafe.entity.Role;
import com.enigma.ICafe.entity.constant.ERole;
import com.enigma.ICafe.repository.RoleRepository;
import com.enigma.ICafe.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(ERole role) {
        return roleRepository.findFirstByRole(role).orElseGet(()
                -> roleRepository.saveAndFlush(Role.builder().role(role).build()));
    }
}
