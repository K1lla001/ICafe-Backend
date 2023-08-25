//package com.enigma.ICafe.service.impl;
//
//import com.enigma.ICafe.entity.computer.TypePrice;
//import com.enigma.ICafe.repository.TypePriceRepository;
//import com.enigma.ICafe.service.TypePriceService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//@Service
//@RequiredArgsConstructor
//public class TypePriceServiceImpl implements TypePriceService {
//
//    private final TypePriceRepository typePriceRepository;
//
//    @Override
//    public TypePrice save(TypePrice typePrice) {
//        return typePriceRepository.saveAndFlush(typePrice);
//    }
//
//    @Override
//    public TypePrice getById(String id) {
//        return typePriceRepository.findById(id).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND, "Price Not Found!")
//                );
//    }
//}
