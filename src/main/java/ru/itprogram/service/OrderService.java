package ru.itprogram.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.Order;
import ru.itprogram.entity.dto.OrderDto;
import ru.itprogram.repository.OrderRepository;
import ru.itprogram.service.converter.order.OrderDtoToOrder;
import ru.itprogram.service.converter.order.OrderToOrderDto;
import ru.itprogram.utils.generater.ArrayListGenerate;

import java.util.List;

public class OrderService implements Service<OrderDto> {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ArrayListGenerate arrayListGenerate;
    @Autowired
    private OrderToOrderDto orderToOrderDto;
    @Autowired
    private OrderDtoToOrder orderDtoToOrder;

    @Override
    public List<OrderDto> getAll() {
        List<Order> orders = orderRepository.getAllEntity();
        List<OrderDto> orderDtoList = arrayListGenerate.getArrayList();
        for (Order order : orders) {
            orderDtoList.add(orderToOrderDto.conversion(order));
        }
        return orderDtoList;
    }

    @Override
    public void add(OrderDto orderDto) {
        orderRepository.saveEntity(orderDtoToOrder.conversion(orderDto));
    }
}
