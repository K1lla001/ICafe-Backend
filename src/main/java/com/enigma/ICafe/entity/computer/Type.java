package com.enigma.ICafe.entity.computer;


import com.enigma.ICafe.entity.auditing.Auditable;
import com.enigma.ICafe.entity.constant.ECategory;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_type")
public class Type extends Auditable<String> {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Enumerated(EnumType.STRING)
    private ECategory category;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<TypePrice> typePrices;

    public List<TypePrice> getTypePrices(){
        return Collections.unmodifiableList(typePrices);
    }
    public void setTypePrices(TypePrice typePrices) {
        this.typePrices.add(typePrices);
    }
}
