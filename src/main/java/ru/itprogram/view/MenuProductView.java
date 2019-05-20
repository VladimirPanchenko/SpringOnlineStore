package ru.itprogram.view;

import ru.itprogram.entity.dto.ProductDto;

import java.util.List;

public class MenuProductView {
    public static void productPrint(List<ProductDto> productDtoList) {
        System.out.println("Для возврата в предыдущие меню введите 0 и нажмите Enter");
        for (ProductDto productDto : productDtoList) {
            System.out.println("ID продукта: [" + productDto.getId()
                    + "] Бренд: [" + productDto.getBrandDto().getName()
                    + "] Тип продукта: [" + productDto.getProductTypeDto()
                    + "] Описание продукта: [" + productDto.getDescription()
                    + "] Гарантия на продукт: [" + productDto.getWarranty()
                    + "] Цена продукта: [" + productDto.getPrice()
                    + "] Скидка на продукт: [" +productDto.getPromoCodeDto().getDiscount());
        }
    }

    public static void addProductPrint() {
        System.out.println("Для добавления товара в корзину введите id товара и нажмите Enter");
    }
}
