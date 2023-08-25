package com.enigma.ICafe.service;

import com.enigma.ICafe.model.common.CustomerSearch;
import com.enigma.ICafe.entity.Customer;
import com.enigma.ICafe.model.request.CustomerRequest;
import com.enigma.ICafe.model.response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface CustomerService {

    Customer addCustomer(Customer customer);

    CustomerResponse updateCustomer(CustomerRequest customer);

    CustomerResponse authenticationCustomer(Authentication authentication);

    String findEmailById(String id);

    CustomerResponse findById(String id);

    Page<CustomerResponse> getCustomerPerPage(Pageable pageable, CustomerSearch customer);

    String deleteCustomer(String id);

}
