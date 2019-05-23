package ru.itprogram.service.converter.product;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.Product;
import ru.itprogram.entity.dto.ProductDto;
import ru.itprogram.repository.ProductRepository;
import ru.itprogram.service.converter.Converter;
import ru.itprogram.service.converter.brand.BrandDtoToBrand;
import ru.itprogram.service.converter.producttype.ProductTypeDtoToProductType;
import ru.itprogram.service.converter.promocode.PromoCodeDtoToPromoCode;
import ru.itprogram.utils.generater.dao.ProductGenerate;

import java.util.List;

public class ProductDtoToProduct implements Converter<Product, ProductDto> {
    @Autowired
    private ProductGenerate productGenerate;
    @Autowired
    private BrandDtoToBrand brandDtoToBrand;
    @Autowired
    private ProductTypeDtoToProductType productTypeDtoToProductType;
    @Autowired
    private PromoCodeDtoToPromoCode promoCodeDtoToPromoCode;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product conversion(ProductDto productDto) {
        Product product = productGenerate.getProduct();
        product.setId(getIdBrand(productDto));
        product.setBrandId(brandDtoToBrand.conversion(productDto.getBrandDto()).getId());
        product.setProductTypeId(productTypeDtoToProductType
                .conversion(productDto.getProductTypeDto()).getId());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setWarranty(productDto.getWarranty());
        product.setAvailable(productDto.isAvailable());
        product.setPrice(productDto.getPrice());
        product.setPromoCodId(promoCodeDtoToPromoCode
                .conversion(productDto.getPromoCodeDto()).getId());
        return product;
    }

    private int getIdBrand(ProductDto productDto) {
        List<Product> products = productRepository.getAllEntity();
        return getId(products, productDto);
    }

    private int getId(List<Product> products, ProductDto productDto) {
        int tempId = 0;
        for (Product product : products){
            if (product.getBrandId() == brandDtoToBrand
                    .conversion(productDto.getBrandDto()).getId()
                    && product.getProductTypeId() == productTypeDtoToProductType
                    .conversion(productDto.getProductTypeDto()).getId()
                    && product.getDescription().equals(productDto.getDescription())
                    && product.getQuantity() == productDto.getQuantity()
                    && product.getWarranty() == productDto.getWarranty()
                    && product.isAvailable() == productDto.isAvailable()
                    && product.getPrice() == productDto.getPrice()
                    && product.getPromoCodId() == promoCodeDtoToPromoCode
                    .conversion(productDto.getPromoCodeDto()).getId()) {
                tempId = product.getId();
            }
        }
        return tempId;
    }
}
