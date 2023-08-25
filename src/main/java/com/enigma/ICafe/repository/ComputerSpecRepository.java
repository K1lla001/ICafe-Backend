package com.enigma.ICafe.repository;

import com.enigma.ICafe.entity.computer.ComputerSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerSpecRepository extends JpaRepository<ComputerSpec, String> {
}
