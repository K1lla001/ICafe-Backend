package com.enigma.ICafe.controller;

import com.enigma.ICafe.dto.common.CommonResponse;
import com.enigma.ICafe.dto.request.ComputerRequest;
import com.enigma.ICafe.dto.response.ComputerResponse;
import com.enigma.ICafe.entity.Computer;
import com.enigma.ICafe.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/computers")
public class ComputerController {

    private final ComputerService computerService;

    @PostMapping
    public ResponseEntity<CommonResponse<ComputerResponse>> addComputer(@RequestBody ComputerRequest request){
        ComputerResponse computerResponse = computerService.addComputer(request);
        return ResponseEntity.ok(
                CommonResponse.<ComputerResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Create New Computer!")
                        .data(computerResponse)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Computer>>> getAll(){
        List<Computer> computersData = computerService.getAll();
        return ResponseEntity.ok(CommonResponse.<List<Computer>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get All Data")
                        .data(computersData)
                .build());
    }

}
