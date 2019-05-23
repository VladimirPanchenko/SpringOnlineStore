package ru.itprogram.service.converter.brand;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.Brand;
import ru.itprogram.entity.dto.BrandDto;
import ru.itprogram.repository.BrandRepository;
import ru.itprogram.service.converter.Converter;
import ru.itprogram.utils.generater.dao.BrandGenerate;

import java.util.List;

public class BrandDtoToBrand implements Converter<Brand, BrandDto> {
    @Autowired
    private BrandGenerate brandGenerate;
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand conversion(BrandDto brandDto) {
        Brand brand = brandGenerate.getBrand();
        brand.setId(getIdBrand(brandDto));
        brand.setName(brandDto.getName());
        return brand;
    }

    private int getIdBrand(BrandDto brandDto) {
        List<Brand> brands = brandRepository.getAllEntity();
        return getId(brands, brandDto);
    }

    private int getId(List<Brand> brands, BrandDto brandDto) {
        int tempId = 0;
        for (Brand brand : brands){
            if (brand.getName().equals(brandDto.getName())) {
                tempId = brand.getId();
            }
        }
        return tempId;
    }
}
