package com.enigma.ICafe.controller;

import com.enigma.ICafe.model.common.CommonResponse;
import com.enigma.ICafe.model.dto.CustomerSearchDTO;
import com.enigma.ICafe.model.common.PagingResponse;
import com.enigma.ICafe.entity.Customer;
import com.enigma.ICafe.model.response.CustomerResponse;
import com.enigma.ICafe.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName){
        CustomerSearchDTO dataCustomer = CustomerSearchDTO.builder()
                .customerFirstName(firstName)
                .customerLastName(lastName)
                .build();
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<CustomerResponse> customers = customerService.getCustomerPerPage(pageable, dataCustomer);

        PagingResponse pagingResponse = PagingResponse.builder()
                .count(customers.getTotalElements())
                .totalPages(customers.getTotalPages())
                .page(pageNumber)
                .size(size)
                .build();

        return ResponseEntity.ok(
                CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get Data Per Page!")
                        .data(customers.getContent())
                        .pagingResponse(pagingResponse)
                        .build()
        );
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> findById(@PathVariable String id){
        CustomerResponse customer = customerService.findById(id);
        return ResponseEntity.ok(
                CommonResponse.<CustomerResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get Customer")
                        .data(customer)
                        .build()
        );
    }
}
