package ru.itprogram.service.converter.producttype;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.ProductType;
import ru.itprogram.entity.dto.ProductTypeDto;
import ru.itprogram.service.converter.Converter;
import ru.itprogram.utils.generater.dto.ProductTypeDtoGenerate;

public class ProductTypeToProductTypeDto implements Converter<ProductTypeDto, ProductType> {
    @Autowired
    private ProductTypeDtoGenerate productTypeDtoGenerate;

    @Override
    public ProductTypeDto conversion(ProductType productType) {
        ProductTypeDto productTypeDto = productTypeDtoGenerate.getProductTypeDto();
        productTypeDto.setType(productType.getType());
        return productTypeDto;
    }
}
