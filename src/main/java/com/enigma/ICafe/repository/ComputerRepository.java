package com.enigma.ICafe.repository;

import com.enigma.ICafe.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, String> {
}
