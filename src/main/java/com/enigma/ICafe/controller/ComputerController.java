package com.enigma.ICafe.controller;

import com.enigma.ICafe.dto.common.CommonResponse;
import com.enigma.ICafe.dto.request.ComputerRequest;
import com.enigma.ICafe.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/computers")
public class ComputerController {
    private final ComputerService computerService;

    @PostMapping
    public ResponseEntity<CommonResponse<ComputerRequest>> create(@RequestBody ComputerRequest request ){
        return null;
    }

}
