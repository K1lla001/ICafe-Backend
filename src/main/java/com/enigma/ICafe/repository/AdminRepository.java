package com.enigma.ICafe.repository;

import com.enigma.ICafe.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findFirstByUserCredential_Email(String email);

}
