package com.enigma.ICafe.entity;

import com.enigma.ICafe.entity.auditing.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "Processor Spec Can Not Be Empty")
    private String processor;
    @NotNull(message = "RAM Spec Can Not Be Empty")
    private String ram;

    @NotNull(message = "Monitor Spec Can Not Be Empty")
    private String monitor;

    @NotNull(message = "SSD Spec Can Not Be Empty")
    private String ssd;

    @NotNull(message = "VGA Spec Can Not Be Empty")
    private String vga;


}
