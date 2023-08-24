package com.enigma.ICafe.service.impl;

import com.enigma.ICafe.dto.request.ComputerRequest;
import com.enigma.ICafe.dto.response.ComputerResponse;
import com.enigma.ICafe.entity.Computer;
import com.enigma.ICafe.entity.ComputerSpec;
import com.enigma.ICafe.entity.Type;
import com.enigma.ICafe.entity.constant.ECategory;
import com.enigma.ICafe.repository.ComputerRepository;
import com.enigma.ICafe.service.ComputerService;
import com.enigma.ICafe.service.ComputerSpecService;
import com.enigma.ICafe.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class ComputerServiceImpl implements ComputerService {

    private final ComputerRepository computerRepository;
    private final TypeService typeService;
    private final ComputerSpecService specService;

    @Override
    public ComputerResponse addComputer(ComputerRequest newComputer) {
        Type computerType = typeService.getOrSave(ECategory.valueOf(newComputer.getCategory()));

        ComputerSpec computerSpec = ComputerSpec.builder()
                .processor(newComputer.getProcessor())
                .ram(newComputer.getRam())
                .monitor(newComputer.getMonitor())
                .ssd(newComputer.getSsd())
                .vga(newComputer.getVga())
                .build();

        specService.addSpec(computerSpec);

        Computer completeComputer = Computer.builder()
                .name(newComputer.getName())
                .code(newComputer.getCode())
                .type(computerType)
                .status(true)
                .specification(computerSpec)
                .build();

        Computer createdComputer = computerRepository.save(completeComputer);

        return responseGenerator(createdComputer);
    }

    @Override
    public List<Computer> getAll() {
        return computerRepository.findAll();
    }

    @Override
    public Page getComputerPerPage(Pageable pageable) {
        //TODO: Get Todo Perpage
        return null;
    }

    @Override
    public ComputerResponse getComputerById(String id) {
        return null;
    }

    @Override
    public ComputerResponse updateComputer(ComputerRequest updateComputer) {
        return null;
    }

    private ComputerResponse responseGenerator(Computer computer) {
        return ComputerResponse.builder()
                .id(computer.getId())
                .name(computer.getName())
                .code(computer.getCode())
                .category(String.valueOf(computer.getType().getCategory()))
                .price(computer.getType().getPrice())
                .processor(computer.getSpecification().getProcessor())
                .ram(computer.getSpecification().getRam())
                .monitor(computer.getSpecification().getMonitor())
                .ssd(computer.getSpecification().getSsd())
                .vga(computer.getSpecification().getVga())
                .build();
    }
}
