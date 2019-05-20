package ru.itprogram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.itprogram.entity.dto.*;
import ru.itprogram.exceptions.UserNotFoundException;
import ru.itprogram.provider.*;
import ru.itprogram.service.*;
import ru.itprogram.utils.ReadProductInFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Configuration
public class SpringConfig {

    @Bean
    @Scope("prototype")
    public Scanner getScanner() {
        return new Scanner(System.in);
    }

    @Bean
    @Scope("prototype")
    public BasketProvider getBasketProvider() {
        return new BasketProvider();
    }

    @Bean
    @Scope("prototype")
    public LoginProvider getLoginProvider(String name, String password) {
        return new LoginProvider(name, password);
    }

    @Bean
    @Scope("prototype")
    public RegistrationProvider getRegistrationProvider() {
        return new RegistrationProvider();
    }

    @Bean
    @Scope("prototype")
    public ReadProductInFile getReadProductInFile() {
        return new ReadProductInFile();
    }

    @Bean
    @Scope("prototype")
    public CatalogProvider getCatalogProvider() {
        return new CatalogProvider();
    }

    @Bean
    @Scope("prototype")
    public PurchaseHistory getPurchaseHistory(UserDto userDto) {
        return new PurchaseHistory(userDto);
    }

    @Bean
    @Scope("prototype")
    public List<OrderDto> getOrderDtoList() {
        return new ArrayList<>();
    }

    @Bean
    @Scope("prototype")
    public List<ProductDto> getProductDtoList() {
        return new ArrayList<>();
    }

    @Bean
    public ProductService getProductService() {
        return new ProductService();

    }

    @Bean
    public BrandService getBrandService() {
        return new BrandService();
    }

    @Bean
    public OrderService getOrderService() {
        return new OrderService();
    }

    @Bean
    public ProductTypeService getProductTypeService() {
        return new ProductTypeService();
    }

    @Bean
    public PromoCodeService getPromoCodeService() {
        return new PromoCodeService();
    }

    @Bean
    public UserService getUserService() {
        return new UserService();
    }

    @Bean
    @Scope("prototype")
    public BrandDto getBrandDto() {
        return new BrandDto();
    }

    @Bean
    @Scope("prototype")
    public OrderDto getOrderDto() {
        return new OrderDto();
    }

    @Bean
    @Scope("prototype")
    public ProductDto getProductDto() {
        return new ProductDto();
    }

    @Bean
    @Scope("prototype")
    public ProductTypeDto getProductTypeDto() {
        return new ProductTypeDto();
    }

    @Bean
    @Scope("prototype")
    public PromoCodeDto getPromoCodeDto() {
        return new PromoCodeDto();
    }

    @Bean
    @Scope("prototype")
    public UserDto getUserDto() {
        return new UserDto();
    }

    @Bean
    public UserNotFoundException getUserNotFoundException() {
        return new UserNotFoundException(LoginProvider.USER_NOT_FOUND);
    }

    @Bean
    public StringBuilder getStringBuilder() {
        return new StringBuilder();
    }
}
