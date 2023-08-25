package com.enigma.ICafe.entity;

import com.enigma.ICafe.entity.auditing.Auditable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "m_customer")
public class Customer extends Auditable<String> {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "is_member")
    private Boolean isMember;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToOne(targetEntity = UserCredential.class)
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;
}
