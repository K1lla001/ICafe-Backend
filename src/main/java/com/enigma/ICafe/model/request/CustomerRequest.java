package com.enigma.ICafe.model.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class CustomerRequest {

    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private @Nullable Boolean isMember;

}
