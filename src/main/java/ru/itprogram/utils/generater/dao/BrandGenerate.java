package ru.itprogram.utils.generater.dao;

import ru.itprogram.entity.dao.Brand;

public abstract class BrandGenerate {
    public Brand getBrand() {
        return createBrand();
    }

    public abstract Brand createBrand();
}
