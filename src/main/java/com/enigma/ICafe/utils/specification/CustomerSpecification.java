package com.enigma.ICafe.utils.specification;

import com.enigma.ICafe.model.common.CustomerSearch;
import com.enigma.ICafe.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {

    public static Specification<Customer> getSpecification(CustomerSearch customerSearch){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if(customerSearch.getCustomerFirstName() != null){
                Predicate firstName = criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + customerSearch.getCustomerFirstName().toLowerCase() + "%");
                predicateList.add(firstName);
            }
            if(customerSearch.getCustomerLastName() != null){
                Predicate lastName = criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + customerSearch.getCustomerLastName().toLowerCase() + "%");
                predicateList.add(lastName);
            }
            Predicate[] predicates = predicateList.toArray(new Predicate[predicateList.size()]);
            return criteriaBuilder.and(predicates);
        };
    }

}
