package com.enigma.ICafe.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "m_admin")
public class Admin {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @OneToOne(targetEntity = UserCredential.class)
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;

}
