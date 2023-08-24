package com.enigma.ICafe.service.impl;

import com.enigma.ICafe.entity.ComputerSpec;
import com.enigma.ICafe.repository.ComputerSpecRepository;
import com.enigma.ICafe.service.ComputerSpecService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComputerSpecServiceImpl implements ComputerSpecService {

    private final ComputerSpecRepository specRepository;

    @Override
    public ComputerSpec addSpec(ComputerSpec newSpec) {
        return specRepository.save(newSpec);
    }

    @Override
    public ComputerSpec updateSpec(ComputerSpec updateSpec) {
        return null;
    }
}
