package com.enigma.ICafe.service;

import com.enigma.ICafe.dto.request.ComputerRequest;
import com.enigma.ICafe.entity.Computer;

public interface ComputerService {

    Computer create(ComputerRequest computer);
}
