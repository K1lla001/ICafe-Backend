package com.enigma.ICafe.entity;

import com.enigma.ICafe.entity.auditing.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_computer")
public class Computer extends Auditable<String> {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @NotNull(message = "Computer Name Can Not Be Empty")
    private String name;

    @NotNull(message = "Computer Code Can Not Be Empty")
    @Column(name = "pc_code", unique = true)
    private String code;

    @OneToOne(targetEntity = Type.class)
    @JoinColumn(name = "type_id")
    private Type type;

    @NotNull(message = "Status Can Not Be Empty")
    private Boolean status;

    @OneToOne(targetEntity = ComputerSpec.class)
    @JoinColumn(name = "computer_spec_id")
    private ComputerSpec specification;



}
