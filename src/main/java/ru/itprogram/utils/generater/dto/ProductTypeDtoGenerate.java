package ru.itprogram.utils.generater.dto;

import ru.itprogram.entity.dto.ProductTypeDto;

public abstract class ProductTypeDtoGenerate {
    public ProductTypeDto getProductTypeDto() {
        return createProductTypeDto();
    }

    public abstract ProductTypeDto createProductTypeDto();
}
