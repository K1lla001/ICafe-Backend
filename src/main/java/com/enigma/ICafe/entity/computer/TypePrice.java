package com.enigma.ICafe.entity.computer;

import com.enigma.ICafe.entity.auditing.Auditable;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "m_type_price")
public class TypePrice extends Auditable<String> {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    private Boolean isActive;

}

