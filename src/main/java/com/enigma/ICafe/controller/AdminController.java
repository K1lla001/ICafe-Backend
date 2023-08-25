package com.enigma.ICafe.controller;

import com.enigma.ICafe.model.common.CommonResponse;
import com.enigma.ICafe.model.request.UpdateAdminRequest;
import com.enigma.ICafe.model.response.AdminResponse;
import com.enigma.ICafe.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    @PutMapping
    public ResponseEntity<CommonResponse<AdminResponse>> update(@RequestBody UpdateAdminRequest request){
        AdminResponse updatedAdmin = adminService.update(request);
        return ResponseEntity.ok(CommonResponse.<AdminResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully update data!")
                        .data(updatedAdmin)
                .build());
    }

}
