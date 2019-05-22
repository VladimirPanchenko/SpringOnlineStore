package ru.itprogram.utils.generater.dao;

import ru.itprogram.entity.dao.ProductType;

public abstract class ProductTypeGenerate {
    public ProductType getProductType() {
        return createProductType();
    }

    public abstract ProductType createProductType();
}
