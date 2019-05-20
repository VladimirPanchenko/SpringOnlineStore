package ru.itprogram.provider;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dto.ProductDto;
import ru.itprogram.service.ProductService;

import javax.annotation.PostConstruct;
import java.util.List;

public class CatalogProvider {
    @Autowired
    private ProductService productService;

    private List<ProductDto> productDtoList;

    @PostConstruct
    private void init() {
        productDtoList = productService.getAll();
    }

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }
}
