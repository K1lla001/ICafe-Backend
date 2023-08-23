package com.enigma.ICafe.service;

import com.enigma.ICafe.entity.Role;
import com.enigma.ICafe.entity.constant.ERole;

public interface RoleService {

    Role getOrSave(ERole role);

}
