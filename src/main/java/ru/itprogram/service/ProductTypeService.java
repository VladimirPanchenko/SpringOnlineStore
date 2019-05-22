package ru.itprogram.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.ProductType;
import ru.itprogram.entity.dto.ProductTypeDto;
import ru.itprogram.repository.ProductTypeRepository;
import ru.itprogram.service.converter.producttype.ProductTypeDtoToProductType;
import ru.itprogram.service.converter.producttype.ProductTypeToProductTypeDto;
import ru.itprogram.utils.generater.ArrayListGenerate;

import java.util.List;

public class ProductTypeService implements Service<ProductTypeDto> {
    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private ArrayListGenerate arrayListGenerate;
    @Autowired
    private ProductTypeToProductTypeDto productTypeToProductTypeDto;
    @Autowired
    private ProductTypeDtoToProductType productTypeDtoToProductType;

    @Override
    public List<ProductTypeDto> getAll() {
        List<ProductType> productTypes = productTypeRepository.getAllEntity();
        List<ProductTypeDto> productTypeDtoList = arrayListGenerate.getArrayList();
        for (ProductType productType : productTypes) {
            productTypeDtoList.add(productTypeToProductTypeDto.conversion(productType));
        }
        return productTypeDtoList;
    }

    @Override
    public void add(ProductTypeDto productTypeDto) {
        new ProductTypeRepository().saveEntity(
                productTypeDtoToProductType.conversion(productTypeDto));
    }
}
