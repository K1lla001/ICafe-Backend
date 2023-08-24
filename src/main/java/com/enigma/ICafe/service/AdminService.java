package com.enigma.ICafe.service;

import com.enigma.ICafe.entity.Admin;

public interface AdminService {

    Admin create(Admin admin);

    Admin update(Admin admin);

    String delete(String id);

}
