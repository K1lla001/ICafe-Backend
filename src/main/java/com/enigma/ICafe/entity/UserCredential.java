package com.enigma.ICafe.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "m_user_credential")
public class UserCredential {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @NotNull(message = "Email Can Not Be Empty")
    private String email;

    @NotNull(message = "Password Can Not Be Empty")
    private String password;

}
