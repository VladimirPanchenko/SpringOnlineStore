package ru.itprogram.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.Product;
import ru.itprogram.entity.dto.ProductDto;
import ru.itprogram.repository.ProductRepository;
import ru.itprogram.service.converter.product.ProductDtoToProduct;
import ru.itprogram.service.converter.product.ProductToProductDto;
import ru.itprogram.utils.generater.ArrayListGenerate;

import java.util.List;

public class ProductService implements Service<ProductDto> {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ArrayListGenerate arrayListGenerate;
    @Autowired
    private ProductToProductDto productToProductDto;
    @Autowired
    private ProductDtoToProduct productDtoToProduct;

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.getAllEntity();
        List<ProductDto> productDtoList = arrayListGenerate.getArrayList();
        for (Product product : products) {
            productDtoList.add(productToProductDto.conversion(product));
        }
        return productDtoList;
    }

    @Override
    public void add(ProductDto productDto) {
        productRepository.saveEntity(productDtoToProduct.conversion(productDto));
    }
}
