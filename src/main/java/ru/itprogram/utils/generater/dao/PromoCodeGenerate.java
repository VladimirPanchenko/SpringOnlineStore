package ru.itprogram.utils.generater.dao;

import ru.itprogram.entity.dao.PromoCode;

public abstract class PromoCodeGenerate {
    public PromoCode getPromoCode() {
        return createPromoCode();
    }

    public abstract PromoCode createPromoCode();
}
