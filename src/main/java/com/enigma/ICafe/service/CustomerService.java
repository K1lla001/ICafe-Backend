package com.enigma.ICafe.service;

import com.enigma.ICafe.model.dto.CustomerSearchDTO;
import com.enigma.ICafe.entity.Customer;
import com.enigma.ICafe.model.request.CustomerRequest;
import com.enigma.ICafe.model.response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    Customer addCustomer(Customer customer);

    CustomerResponse updateCustomer(CustomerRequest customer);

    String findEmailById(String id);

    CustomerResponse findById(String id);

    Page<CustomerResponse> getCustomerPerPage(Pageable pageable, CustomerSearchDTO customer);

    String deleteCustomer(String id);

}
