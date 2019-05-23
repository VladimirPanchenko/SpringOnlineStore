package ru.itprogram.service.converter.order;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.Order;
import ru.itprogram.entity.dto.OrderDto;
import ru.itprogram.repository.OrderRepository;
import ru.itprogram.service.converter.Converter;
import ru.itprogram.service.converter.product.ProductDtoToProduct;
import ru.itprogram.service.converter.user.UserDtoToUser;
import ru.itprogram.utils.generater.dao.OrderGenerate;

import java.util.List;

public class OrderDtoToOrder implements Converter<Order, OrderDto> {
    @Autowired
    private OrderGenerate orderGenerate;
    @Autowired
    private UserDtoToUser userDtoToUser;
    @Autowired
    private ProductDtoToProduct productDtoToProduct;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order conversion(OrderDto orderDto) {
        Order order = orderGenerate.getOrder();
        order.setId(getIdOrder(orderDto));
        order.setUserId(userDtoToUser.conversion(orderDto.getUserDto()).getId());
        order.setProductId(productDtoToProduct.conversion(orderDto.getProductDto()).getId());
        order.setPaid(orderDto.isPaid());
        order.setClosed(orderDto.isClosed());
        order.setOrderDate(orderDto.getOrderDate());
        return order;
    }

    private int getIdOrder(OrderDto orderDto) {
        List<Order> orders = orderRepository.getAllEntity();
        return getId(orders, orderDto);
    }

    private int getId(List<Order> orders, OrderDto orderDto) {
        int tempId = 0;
        for (Order order : orders){
            if (order.getUserId() == userDtoToUser.conversion(orderDto.getUserDto()).getId()
                    && order.getProductId() == productDtoToProduct
                    .conversion(orderDto.getProductDto()).getId()
                    && order.isPaid() == orderDto.isPaid()
                    && order.isClosed() == orderDto.isClosed()
                    && order.getOrderDate().equals(orderDto.getOrderDate())) {
                tempId = order.getId();
            }
        }
        return tempId;
    }
}
