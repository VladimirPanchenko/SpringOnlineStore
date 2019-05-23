package ru.itprogram.service.converter.brand;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.Brand;
import ru.itprogram.entity.dto.BrandDto;
import ru.itprogram.service.converter.Converter;
import ru.itprogram.utils.generater.dto.BrandDtoGenerate;

public class BrandToBrandDto implements Converter<BrandDto, Brand> {
    @Autowired
    private BrandDtoGenerate brandDtoGenerate;

    @Override
    public BrandDto conversion(Brand brand) {
        BrandDto brandDto = brandDtoGenerate.getBrandDto();
        brandDto.setName(brand.getName());
        return brandDto;
    }
}
