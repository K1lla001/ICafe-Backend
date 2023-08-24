package com.enigma.ICafe.repository;

import com.enigma.ICafe.entity.Admin;
import com.enigma.ICafe.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {
    List<Customer> findCustomerByFirstNameOrLastNameContainingIgnoreCase(String firstName, String lastName);

    ;
}
