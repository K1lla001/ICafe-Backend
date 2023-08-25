package com.enigma.ICafe.service.impl;

import com.enigma.ICafe.entity.Admin;
import com.enigma.ICafe.entity.UserDetailsImpl;
import com.enigma.ICafe.model.common.CustomerSearch;
import com.enigma.ICafe.entity.Customer;
import com.enigma.ICafe.model.request.CustomerRequest;
import com.enigma.ICafe.model.response.CustomerResponse;
import com.enigma.ICafe.repository.CustomerRepository;
import com.enigma.ICafe.service.CustomerService;
import com.enigma.ICafe.utils.specification.CustomerSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse authenticationCustomer(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Customer customer = customerRepository.findFirstByUserCredential_Email(userDetails.getEmail()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        return responseGenerator(customer);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email Already Exist!");
        }
    }

    @Override

    public CustomerResponse updateCustomer(CustomerRequest customer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomerResponse customerResponse = authenticationCustomer(authentication);
        if(customerResponse.getIsDeleted() != null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account is already deleted!");
        if(customerResponse.getId().equals(customer.getId())){
            Customer foundCustomer = getCustomerById(customer.getId());
            log.info(foundCustomer.toString());
            foundCustomer.setFirstName(customer.getFirstName());
            foundCustomer.setLastName(customer.getLastName());
            foundCustomer.setPhoneNumber(customer.getPhoneNumber());
            foundCustomer.setIsMember(customer.getIsMember());
            foundCustomer.setIsDeleted(customer.getIsDeleted());
            customerRepository.save(foundCustomer);

            return responseGenerator(foundCustomer);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You're not allowed to access this resource!");
    }

    @Override
    public CustomerResponse findById(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        return responseGenerator(customer);
    }

    @Override
    public String findEmailById(String id) {
        Customer customer = customerRepository.findFirstByUserCredential_Email(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        return customer.getUserCredential().getEmail();
    }

    @Override
    public Page<CustomerResponse> getCustomerPerPage(Pageable pageable, CustomerSearch customerDto) {
        Specification<Customer> customerSpecification = CustomerSpecification.getSpecification(customerDto);
        Page<Customer> customers = customerRepository.findAll(customerSpecification, pageable);
        return customers.map(this::responseGenerator);
    }

    @Override
    public String deleteCustomer(String id) {
        Customer customer = getCustomerById(id);
        customer.setIsDeleted(true);
        customer.getUserCredential().setIsActive(false);
        customerRepository.save(customer);
        return id;
    }

    private Customer getCustomerById(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Service: User not found!"));
        return customer;
    }

    private CustomerResponse responseGenerator(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .fullName(customer.getFirstName() + " " + customer.getLastName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .isMember(customer.getIsMember())
                .role(customer.getUserCredential().getRole().getRole().name())
                .build();
    }
}
