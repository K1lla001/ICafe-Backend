package com.enigma.ICafe.model.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class CustomerSearchDTO {
    private String customerFirstName;
    private String customerLastName;
}
