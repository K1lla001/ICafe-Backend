package com.enigma.ICafe.service.impl;

import com.enigma.ICafe.dto.request.ComputerRequest;
import com.enigma.ICafe.entity.Computer;
import com.enigma.ICafe.entity.ComputerSpec;
import com.enigma.ICafe.entity.Type;
import com.enigma.ICafe.entity.constant.ECategory;
import com.enigma.ICafe.repository.ComputerRepository;
import com.enigma.ICafe.service.ComputerService;
import com.enigma.ICafe.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComputerServiceImpl implements ComputerService {
    private final ComputerRepository computerRepository;
    private final TypeService typeService;

    @Override
    public Computer create(ComputerRequest computer) {

        Type type = typeService.getOrSave(ECategory.valueOf(computer.getType()));
        ComputerSpec spec = ComputerSpec.builder()
                .vga(computer.getVga())
                .ssd(computer.getSsd())
                .ram(computer.getRam())
                .monitor(computer.getMonitor())
                .processor(computer.getProcessor())
                .build();

        String code = computer.getType() + computerRepository.findAllByType(type).size()+1;
        Computer pc = Computer.builder()
                .code(code)
                .name(computer.getName())
                .specification(spec)
                .type(type)
                .status(true)
                .build();
        return computerRepository.saveAndFlush(pc);
    }
}
