package ru.itprogram.service.converter.order;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.Order;
import ru.itprogram.entity.dao.Product;
import ru.itprogram.entity.dao.User;
import ru.itprogram.entity.dto.OrderDto;
import ru.itprogram.repository.ProductRepository;
import ru.itprogram.repository.UserRepository;
import ru.itprogram.service.converter.Converter;
import ru.itprogram.service.converter.product.ProductToProductDto;
import ru.itprogram.service.converter.user.UserToUserDto;
import ru.itprogram.utils.generater.ArrayListGenerate;
import ru.itprogram.utils.generater.dto.OrderDtoGenerate;

import java.util.List;

public class OrderToOrderDto implements Converter<OrderDto, Order> {
    @Autowired
    private UserToUserDto userToUserDto;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArrayListGenerate arrayListGenerate;
    @Autowired
    private OrderDtoGenerate orderDtoGenerate;
    @Autowired
    private ProductToProductDto productToProductDto;

    @Override
    public OrderDto conversion(Order order) {
        OrderDto orderDto = orderDtoGenerate.getOrderDto();
        List<User> users = userRepository.getAllEntity();
        User user = null;
        for (User findUser : users) {
            if (findUser.getId() == order.getUserId()) {
                user = findUser;
            }
        }
        List<Product> products = productRepository.getAllEntity();
        Product product = null;
        for (Product findProduct : products) {
            if (findProduct.getId() == order.getProductId()) {
                product = findProduct;
            }
        }
        orderDto.setUserDto(userToUserDto.conversion(user));
        orderDto.setProductDto(productToProductDto.conversion(product));
        orderDto.setPaid(order.isPaid());
        orderDto.setClosed(order.isClosed());
        orderDto.setOrderDate(order.getOrderDate());
        return orderDto;
    }
}
