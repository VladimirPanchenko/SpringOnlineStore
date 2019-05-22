package ru.itprogram.provider;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dto.OrderDto;
import ru.itprogram.entity.dto.UserDto;
import ru.itprogram.service.OrderService;

import javax.annotation.PostConstruct;
import java.util.List;

public class PurchaseHistoryProvider {
    @Autowired
    private OrderService orderService;
    private List<OrderDto> orderDtoList;
    private UserDto userDto;

    public PurchaseHistoryProvider(UserDto userDto) {
        this.userDto = userDto;
    }

    @PostConstruct
    private void init() {
        orderDtoList = orderService.getAll();
    }

    public List<OrderDto> getOrderDtoList() {
        return orderDtoList;
    }

    public void printAllHistory() {
        int count = 0;
        System.out.println("Покупки совершённые вами:");
        for (OrderDto orderDto : orderDtoList) {
            if (orderDto.getUserDto().equals(userDto)) {
                ++count;
                System.out.println("ID продукта: [" + orderDto.getProductDto().getId()
                        + "] Бренд: [" + orderDto.getProductDto().getBrandDto().getName()
                        + "] Тип продукта: [" + orderDto.getProductDto()
                        .getProductTypeDto().getType()
                        + "] Описание: [" + orderDto.getProductDto().getDescription()
                        + "] Гарантия: [" + orderDto.getProductDto().getWarranty()
                        + "] Цена: [" + orderDto.getProductDto().getPrice()
                        + "] Оплачен ли товар: [" + orderDto.isPaid()
                        + "] Дата покупки: [" + orderDto.getOrderDate());
            }
        }
        if (count == 0) {
            System.out.println("Похоже, что вы ещё не совершали покупок.");
        }
    }
}
