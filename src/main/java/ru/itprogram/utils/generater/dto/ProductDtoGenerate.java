package ru.itprogram.utils.generater.dto;

import ru.itprogram.entity.dto.ProductDto;

public abstract class ProductDtoGenerate {
    public ProductDto getProductDto() {
        return createProductDto();
    }

    public abstract ProductDto createProductDto();
}
