package com.enigma.ICafe.utils;

import com.enigma.ICafe.dto.common.CustomerSearchDTO;
import com.enigma.ICafe.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {

    public static Specification<Customer> getSpecification(CustomerSearchDTO customerSearchDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if(customerSearchDTO.getCustomerFirstName() != null){
                Predicate firstName = criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + customerSearchDTO.getCustomerFirstName().toLowerCase() + "%");
                predicateList.add(firstName);
            }
            if(customerSearchDTO.getCustomerLastName() != null){
                Predicate lastName = criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + customerSearchDTO.getCustomerFirstName().toLowerCase() + "%");
                predicateList.add(lastName);
            }
            Predicate[] predicates = predicateList.toArray(new Predicate[predicateList.size()]);
            return criteriaBuilder.and(predicates);
        };
    }

}
