package com.enigma.ICafe.dto.request;


import com.enigma.ICafe.entity.Type;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ComputerRequest {

    @NotNull(message = "Computer Name Can Not Be Empty")
    private String name;


    @NotNull(message = "Computer Type Can Not Be Empty")
    private String  type;

    @NotNull(message = "Processor Spec Can Not Be Empty")
    private String processor;

    @NotNull(message = "RAM Spec Can Not Be Empty")
    private String ram;

    @NotNull(message = "Monitor Spec Can Not Be Empty")
    private String monitor;

    @NotNull(message = "SSD Spec Can Not Be Empty")
    private String ssd;

    @NotNull(message = "VGA Spec Can Not Be Empty")
    private String vga;

}
