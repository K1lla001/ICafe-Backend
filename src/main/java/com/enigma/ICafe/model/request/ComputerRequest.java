package com.enigma.ICafe.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NotBlank
@NoArgsConstructor
@AllArgsConstructor
public class ComputerRequest {
    private String name;
    private String code;
    private String category;
    private String price;
    private String processor;
    private String ram;
    private String monitor;
    private String ssd;
    private String vga;
}
