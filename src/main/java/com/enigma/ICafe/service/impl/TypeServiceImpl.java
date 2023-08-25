package com.enigma.ICafe.service.impl;

import com.enigma.ICafe.entity.computer.Type;
import com.enigma.ICafe.entity.constant.ECategory;
import com.enigma.ICafe.repository.TypeRepository;
import com.enigma.ICafe.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    @Override
    public Type getOrSave(ECategory category) {
        return typeRepository.findByCategory(category).orElseGet(() ->
            typeRepository.saveAndFlush(Type.builder()
                    .category(category)

                    .build()));
    }

    @Override
    public Long setPrice(ECategory category, Long updatePrice) {
        long price = 0L;

        if (category.equals(ECategory.EXTREME)) {
            price = 10_000L + updatePrice;
        } else if (category.equals(ECategory.VIP)) {
            price = 8_000L + updatePrice;
        } else if (category.equals(ECategory.REGULAR)) {
            price = 6_000L + updatePrice;
        }

        return price;
    }

}
