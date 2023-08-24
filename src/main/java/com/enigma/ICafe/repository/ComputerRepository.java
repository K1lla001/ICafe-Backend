package com.enigma.ICafe.repository;

import com.enigma.ICafe.entity.Computer;
import com.enigma.ICafe.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, String> {
    List<Computer> findAllByType(Type type);
}
