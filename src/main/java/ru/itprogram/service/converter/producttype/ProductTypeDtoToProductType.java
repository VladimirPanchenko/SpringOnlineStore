package ru.itprogram.service.converter.producttype;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.ProductType;
import ru.itprogram.entity.dto.ProductTypeDto;
import ru.itprogram.repository.ProductTypeRepository;
import ru.itprogram.service.converter.Converter;
import ru.itprogram.utils.generater.dao.ProductTypeGenerate;

import java.util.List;

public class ProductTypeDtoToProductType implements Converter<ProductType, ProductTypeDto> {
    @Autowired
    private ProductTypeGenerate productTypeGenerate;
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public ProductType conversion(ProductTypeDto productTypeDto) {
        ProductType productType = productTypeGenerate.getProductType();
        productType.setId(getIdType(productTypeDto));
        productType.setType(productTypeDto.getType());
        return productType;
    }
    private int getIdType(ProductTypeDto productTypeDto) {
        List<ProductType> productTypes = productTypeRepository.getAllEntity();
        return getId(productTypes, productTypeDto);
    }

    private int getId(List<ProductType> productTypes, ProductTypeDto productTypeDto) {
        int tempId = 0;
        for (ProductType productType : productTypes){
            if (productType.getType().equals(productTypeDto.getType())) {
                tempId = productType.getId();
            }
        }
        return tempId;
    }
}
