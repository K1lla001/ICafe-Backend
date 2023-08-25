package com.enigma.ICafe.model.response;

import com.enigma.ICafe.entity.computer.TypePrice;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ComputerResponse {
    private String id;
    private String name;
    private String code;
    private String category;
    private List<TypePrice> prices;
    private String processor;
    private String ram;
    private String monitor;
    private String ssd;
    private String vga;

}
