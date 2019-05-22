package ru.itprogram.utils.generater.dao;

import ru.itprogram.entity.dao.Product;

public abstract class ProductGenerate {
    public Product getProduct() {
        return createProduct();
    }

    public abstract Product createProduct();
}
