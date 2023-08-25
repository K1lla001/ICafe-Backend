package com.enigma.ICafe.model.common;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class CustomerSearch {
    private String customerFirstName;
    private String customerLastName;
}
