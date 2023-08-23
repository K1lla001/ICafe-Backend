package com.enigma.ICafe.entity;


import jakarta.persistence.*;
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
@Entity(name = "m_admin")
public class Admin {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "email", unique = true)
    @NotNull(message = "Email Can Not Be Empty")
    private String email;

    @Column(name = "full_name")
    @NotNull(message = "full name Can Not Be Empty")
    private String fullName;

    @Column(name = "phone_number", unique = true)
    @NotNull(message = "Phone Number Can Not Be Empty")
    private String phoneNumber;


    @OneToOne(targetEntity = UserCredential.class)
    private UserCredential userCredential;

}
