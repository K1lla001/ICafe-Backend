package com.enigma.ICafe.entity;


import com.enigma.ICafe.entity.constant.ERole;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "m_role")
public class Role {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Enumerated(EnumType.STRING)
    private ERole role;

}
