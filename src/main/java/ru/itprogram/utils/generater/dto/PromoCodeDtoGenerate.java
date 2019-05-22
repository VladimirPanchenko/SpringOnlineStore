package ru.itprogram.utils.generater.dto;

import ru.itprogram.entity.dto.PromoCodeDto;

public abstract class PromoCodeDtoGenerate {
    public PromoCodeDto getPromoCodeDto() {
        return createPromoCodeDto();
    }

    public abstract PromoCodeDto createPromoCodeDto();
}
