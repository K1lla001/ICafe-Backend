package com.enigma.ICafe.service;

import com.enigma.ICafe.entity.Type;
import com.enigma.ICafe.entity.constant.ECategory;

public interface TypeService {

    Type getOrSave(ECategory category);

    Long setPrice(ECategory category, Long updatePrice);

}
