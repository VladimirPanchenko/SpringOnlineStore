package ru.itprogram.utils.generater.dto;

import ru.itprogram.entity.dto.BrandDto;

public abstract class BrandDtoGenerate {
    public BrandDto getBrandDto() {
        return createBrandDto();
    }

    public abstract BrandDto createBrandDto();
}
