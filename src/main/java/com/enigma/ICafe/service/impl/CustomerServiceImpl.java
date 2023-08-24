package com.enigma.ICafe.service.impl;

import com.enigma.ICafe.model.dto.CustomerSearchDTO;
import com.enigma.ICafe.entity.Customer;
import com.enigma.ICafe.model.request.CustomerRequest;
import com.enigma.ICafe.model.response.CustomerResponse;
import com.enigma.ICafe.repository.CustomerRepository;
import com.enigma.ICafe.service.CustomerService;
import com.enigma.ICafe.utils.specification.CustomerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        try {
            return customerRepository.save(customer);
        }catch (DataIntegrityViolationException exception){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email Already Exist!");
        }
    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest customer) {
        CustomerResponse customerId = findById(customer.getId());

        Customer updatedCustomer = Customer.builder()
                .id(customerId.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phoneNumber(customer.getPhoneNumber())
                .isMember(customer.getIsMember())
                .build();

        customerRepository.save(updatedCustomer);

        return responseGenerator(updatedCustomer);
    }

    @Override
    public CustomerResponse findById(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
       return responseGenerator(customer);
    }

    @Override
    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponse> responses = new ArrayList<>();
        customers.forEach(customer -> responses.add(responseGenerator(customer)));
        return responses;
    }

    @Override
    public Page<CustomerResponse> getCustomerPerPage(Pageable pageable, CustomerSearchDTO customerDto) {
        Specification<Customer> customerSpecification = CustomerSpecification.getSpecification(customerDto);
        Page<Customer> customers = customerRepository.findAll(customerSpecification, pageable);
        return customers.map(this::responseGenerator);
    }

    @Override
    public String deleteCustomer(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        customer.setIsDeleted(true);
        customer.getUserCredential().setIsActive(false);
        customerRepository.save(customer);
        return id;
    }

    private CustomerResponse responseGenerator(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .fullName(customer.getFirstName() +" "+ customer.getLastName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .isMember(customer.getIsMember())
                .role(customer.getUserCredential().getRole().getRole().name())
                .build();
    }
}