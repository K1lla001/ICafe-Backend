//package com.enigma.ICafe.service.impl;
//
//import com.enigma.ICafe.entity.computer.TypePrice;
//import com.enigma.ICafe.model.request.ComputerRequest;
//import com.enigma.ICafe.model.response.ComputerResponse;
//import com.enigma.ICafe.entity.computer.Computer;
//import com.enigma.ICafe.entity.computer.ComputerSpec;
//import com.enigma.ICafe.entity.computer.Type;
//import com.enigma.ICafe.entity.constant.ECategory;
//import com.enigma.ICafe.repository.ComputerRepository;
//import com.enigma.ICafe.service.ComputerService;
//import com.enigma.ICafe.service.ComputerSpecService;
//import com.enigma.ICafe.service.TypePriceService;
//import com.enigma.ICafe.service.TypeService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(rollbackOn = Exception.class)
//public class ComputerServiceImpl implements ComputerService {
//
//    private final ComputerRepository computerRepository;
//    private final TypeService typeService;
//    private final TypePriceService typePriceService;
//    private final ComputerSpecService specService;
//
//    @Override
//    public ComputerResponse addComputer(ComputerRequest newComputer) {
//
//    }
//
//    @Override
//    public List<Computer> getAll() {
//        return computerRepository.findAll();
//    }
//
//    @Override
//    public Page<Computer> getComputerPerPage(Pageable pageable) {
//        //TODO: Get Todo Perpage
//        return null;
//    }
//
//    @Override
//    public ComputerResponse getComputerById(String id) {
//        return null;
//    }
//
//    @Override
//    public ComputerResponse updateComputer(ComputerRequest updateComputer) {
//        return null;
//    }
//
//    private ComputerResponse responseGenerator(Computer computer) {
//        return ComputerResponse.builder()
//                .id(computer.getId())
//                .name(computer.getName())
//                .code(computer.getCode())
//                .category(String.valueOf(computer.getType().getCategory()))
//                .prices(computer.getType().getTypePrices())
//                .processor(computer.getSpecification().getProcessor())
//                .ram(computer.getSpecification().getRam())
//                .monitor(computer.getSpecification().getMonitor())
//                .ssd(computer.getSpecification().getSsd())
//                .vga(computer.getSpecification().getVga())
//                .build();
//    }
//}
