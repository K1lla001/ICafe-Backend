package com.enigma.ICafe.repository;

import com.enigma.ICafe.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Admin, String> {
}
