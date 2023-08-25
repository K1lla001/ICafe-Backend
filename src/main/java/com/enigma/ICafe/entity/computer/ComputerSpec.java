package com.enigma.ICafe.entity.computer;

import com.enigma.ICafe.entity.auditing.Auditable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "m_computer_spec")
public class ComputerSpec extends Auditable<String> {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    private String processor;
    private String ram;
    private String monitor;
    private String ssd;
    private String vga;


}
