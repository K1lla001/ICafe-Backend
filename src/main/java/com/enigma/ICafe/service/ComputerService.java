package com.enigma.ICafe.service;

import com.enigma.ICafe.dto.request.ComputerRequest;
import com.enigma.ICafe.dto.response.ComputerResponse;
import com.enigma.ICafe.entity.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComputerService {

    ComputerResponse addComputer(ComputerRequest newComputer);
    List<Computer> getAll();

    Page getComputerPerPage (Pageable pageable);
    ComputerResponse getComputerById(String id);
    ComputerResponse updateComputer(ComputerRequest updateComputer);
}
