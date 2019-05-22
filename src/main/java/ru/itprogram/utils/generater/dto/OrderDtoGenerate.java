package ru.itprogram.utils.generater.dto;

import ru.itprogram.entity.dto.OrderDto;

public abstract class OrderDtoGenerate {
    public OrderDto getOrderDto() {
        return createOrderDto();
    }

    public abstract OrderDto createOrderDto();
}
