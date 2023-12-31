package com.enigma.ICafe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "m_customer")
public class Customer {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "first_name")
    @NotNull(message = "First Name Can Not Be Empty")
    @Length(min = 3)
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last Name Can Not Be Empty")
    @Length(min = 3)
    private String lastName;

    @Column(name = "email", unique = true)
    @NotNull(message = "Email Can Not Be Empty")
    private String email;

    @Column(name = "phone_number", unique = true)
    @NotNull(message = "Phone Number Can Not Be Empty")
    private String phoneNumber;

    @Column(name = "is_member")
    private Boolean isMember;

    @OneToOne(targetEntity = UserCredential.class)
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;
}
