package com.enigma.ICafe.repository;

import com.enigma.ICafe.entity.Type;
import com.enigma.ICafe.entity.constant.ECategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, String> {

    Optional<Type> findByType(ECategory category);
}
