package com.enigma.ICafe.dto.request;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NotBlank
public class ComputerRequest {
    private String name;
    private String code;
    private String category;
    private String processor;
    private String ram;
    private String monitor;
    private String ssd;
    private String vga;
}
