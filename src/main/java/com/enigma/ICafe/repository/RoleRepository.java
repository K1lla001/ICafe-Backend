package com.enigma.ICafe.repository;

import com.enigma.ICafe.entity.Role;
import com.enigma.ICafe.entity.constant.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findFirstByRole(ERole role);
}
