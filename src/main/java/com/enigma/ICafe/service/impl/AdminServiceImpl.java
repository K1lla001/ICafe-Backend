package com.enigma.ICafe.service.impl;

import com.enigma.ICafe.entity.Admin;
import com.enigma.ICafe.repository.AdminRepository;
import com.enigma.ICafe.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Admin create(Admin admin) {
        try {
            return adminRepository.save(admin);
        }catch (DataIntegrityViolationException exception){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email Already Exist");
        }
    }

    @Override
    public Admin update(Admin admin) {
        //TODO: Update, Should Return Updated Admin
        return null;
    }

    @Override
    public String delete(String id) {
        //TODO: Delete, Should Return Deleted Admin ID
        return null;
    }
}
