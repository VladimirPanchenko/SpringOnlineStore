package ru.itprogram.service.converter.product;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.Brand;
import ru.itprogram.entity.dao.Product;
import ru.itprogram.entity.dao.ProductType;
import ru.itprogram.entity.dao.PromoCode;
import ru.itprogram.entity.dto.BrandDto;
import ru.itprogram.entity.dto.ProductDto;
import ru.itprogram.entity.dto.ProductTypeDto;
import ru.itprogram.entity.dto.PromoCodeDto;
import ru.itprogram.repository.BrandRepository;
import ru.itprogram.repository.ProductTypeRepository;
import ru.itprogram.repository.PromoCodeRepository;
import ru.itprogram.service.converter.Converter;
import ru.itprogram.service.converter.brand.BrandToBrandDto;
import ru.itprogram.service.converter.producttype.ProductTypeToProductTypeDto;
import ru.itprogram.service.converter.promocode.PromoCodeToPromoCodeDto;
import ru.itprogram.utils.generater.dto.BrandDtoGenerate;
import ru.itprogram.utils.generater.dto.ProductDtoGenerate;
import ru.itprogram.utils.generater.dto.ProductTypeDtoGenerate;

import java.util.List;

public class ProductToProductDto implements Converter<ProductDto, Product> {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandDtoGenerate brandDtoGenerate;
    @Autowired
    private BrandToBrandDto brandToBrandDto;
    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private ProductTypeDtoGenerate productTypeDtoGenerate;
    @Autowired
    private ProductTypeToProductTypeDto productTypeToProductTypeDto;
    @Autowired
    private PromoCodeRepository promoCodeRepository;
    @Autowired
    private PromoCodeToPromoCodeDto promoCodeToPromoCodeDto;
    @Autowired
    private ProductDtoGenerate productDtoGenerate;

    @Override
    public ProductDto conversion(Product product) {
        List<Brand> brands = brandRepository.getAllEntity();
        BrandDto brandDto = brandDtoGenerate.getBrandDto();
        for (Brand brand : brands) {
            if (brand.getId() == product.getBrandId()) {
                brandDto = brandToBrandDto.conversion(brand);
            }
        }
        List<ProductType> productTypes = productTypeRepository.getAllEntity();
        ProductTypeDto productTypeDto = null;
        for (ProductType productType : productTypes) {
            if (productType.getId() == product.getProductTypeId()) {
                productTypeDto = productTypeToProductTypeDto.conversion(productType);
            }
        }
        List<PromoCode> promoCodes = promoCodeRepository.getAllEntity();
        PromoCodeDto promoCodeDto = null;
        for (PromoCode promoCode : promoCodes) {
            if (promoCode.getId() == product.getPromoCodId()) {
                promoCodeDto = promoCodeToPromoCodeDto.conversion(promoCode);
            }
        }
        ProductDto productDto = productDtoGenerate.getProductDto();
        productDto.setId(product.getId());
        productDto.setBrandDto(brandDto);
        productDto.setProductTypeDto(productTypeDto);
        productDto.setDescription(product.getDescription());
        productDto.setQuantity(product.getQuantity());
        productDto.setWarranty(product.getWarranty());
        productDto.setAvailable(product.isAvailable());
        productDto.setPrice(product.getPrice());
        productDto.setPromoCodeDto(promoCodeDto);
        return productDto;
    }
}
