package com.enigma.ICafe.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "m_user_credential")
public class UserCredential {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "email", unique = true)
    private String email;

    private String password;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private Boolean isActive;


    @OneToOne(mappedBy = "user")
    private ProfilePicture profilePicture;
}
