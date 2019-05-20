package ru.itprogram.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itprogram.entity.dto.OrderDto;
import ru.itprogram.entity.dto.ProductDto;
import ru.itprogram.entity.dto.UserDto;
import ru.itprogram.service.OrderService;
import ru.itprogram.service.ProductService;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Component
public class BasketProvider {
    @Autowired
    private ProductService productService;
    private List<ProductDto> productDtoList;
    @Autowired
    private List<OrderDto> orderDtoList;
    @Autowired
    private OrderService orderService;

    public List<OrderDto> getOrderDtoList() {
        Iterator iterator = orderDtoList.iterator();
        while (iterator.hasNext()) {
            OrderDto orderDto = (OrderDto) iterator.next();
            if (orderDto.getUserDto() == null) {
                iterator.remove();
            }
        }
        return orderDtoList;
    }

    public OrderDto getOrderDto(int id) {
        OrderDto orderDto = null;
        for (OrderDto findOrderDto : orderDtoList) {
            if (orderDto.getProductDto().getId() == id) {
                orderDto = findOrderDto;
            }
        }
        return orderDto;
    }

    public void addProduct(int idProduct, UserDto userDto) {
        productDtoList = productService.getAll();
        for (ProductDto productDto : productDtoList) {
            if (productDto.getId() == idProduct) {
                orderDtoList
                        .add(new OrderDto(userDto, productDto, false, false, LocalDateTime.now()));
            }
        }
    }

    public void RemoveProduct(int idProduct, UserDto userDto) {
        Iterator iterator = orderDtoList.iterator();
        while (iterator.hasNext()) {
            OrderDto orderDto = (OrderDto) iterator.next();
            if (orderDto.getProductDto().getId() == idProduct
                    && orderDto.getUserDto().equals(userDto)) {
                iterator.remove();
            }
        }
    }

    public void buy() {
        for (OrderDto orderDto : orderDtoList) {
            orderService.add(orderDto);
        }
    }

    public Double allPrice() {
        double price = 0.0;
        for (OrderDto orderDto : orderDtoList) {
            price += orderDto.getProductDto().getPrice() -
                            orderDto.getProductDto().getPromoCodeDto().getDiscount();
        }
        return price;
    }
}
