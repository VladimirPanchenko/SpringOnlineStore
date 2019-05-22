package ru.itprogram.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.Brand;
import ru.itprogram.entity.dto.BrandDto;
import ru.itprogram.repository.BrandRepository;
import ru.itprogram.service.converter.brand.BrandDtoToBrand;
import ru.itprogram.service.converter.brand.BrandToBrandDto;
import ru.itprogram.utils.generater.ArrayListGenerate;

import java.util.List;

public class BrandService implements Service<BrandDto> {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ArrayListGenerate arrayListGenerate;
    @Autowired
    private BrandToBrandDto brandToBrandDto;
    @Autowired
    private BrandDtoToBrand brandDtoToBrand;

    @Override
    public List<BrandDto> getAll() {
        List<Brand> brands = brandRepository.getAllEntity();
        List<BrandDto> brandDtoList = arrayListGenerate.getArrayList();
        for (Brand brand : brands) {
            brandDtoList.add(brandToBrandDto.conversion(brand));
        }
        return brandDtoList;
    }

    @Override
    public void add(BrandDto brandDto) {
        brandRepository.saveEntity(brandDtoToBrand.conversion(brandDto));
    }
}
