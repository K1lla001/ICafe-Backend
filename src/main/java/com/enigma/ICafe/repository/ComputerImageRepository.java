package com.enigma.ICafe.repository;

import com.enigma.ICafe.entity.computer.ComputerImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerImageRepository extends JpaRepository<ComputerImage, String> {
}
