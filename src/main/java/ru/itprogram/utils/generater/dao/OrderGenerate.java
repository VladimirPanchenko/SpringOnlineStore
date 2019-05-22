package ru.itprogram.utils.generater.dao;

import ru.itprogram.entity.dao.Order;

public abstract class OrderGenerate {
    public Order getOrder() {
        return createOrder();
    }

    public abstract Order createOrder();
}
