package ru.itprogram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import ru.itprogram.entity.dao.*;
import ru.itprogram.entity.dto.*;
import ru.itprogram.exceptions.UserNameIsRepeatedException;
import ru.itprogram.exceptions.UserNotFoundException;
import ru.itprogram.provider.*;
import ru.itprogram.repository.*;
import ru.itprogram.service.*;
import ru.itprogram.service.converter.brand.BrandDtoToBrand;
import ru.itprogram.service.converter.brand.BrandToBrandDto;
import ru.itprogram.service.converter.order.OrderDtoToOrder;
import ru.itprogram.service.converter.order.OrderToOrderDto;
import ru.itprogram.service.converter.product.ProductDtoToProduct;
import ru.itprogram.service.converter.product.ProductToProductDto;
import ru.itprogram.service.converter.producttype.ProductTypeDtoToProductType;
import ru.itprogram.service.converter.producttype.ProductTypeToProductTypeDto;
import ru.itprogram.service.converter.promocode.PromoCodeDtoToPromoCode;
import ru.itprogram.service.converter.promocode.PromoCodeToPromoCodeDto;
import ru.itprogram.service.converter.user.UserDtoToUser;
import ru.itprogram.service.converter.user.UserToUserDto;
import ru.itprogram.utils.CurrentConnection;
import ru.itprogram.utils.ReadProductInFile;
import ru.itprogram.utils.generater.ArrayListGenerate;
import ru.itprogram.utils.generater.CurrentConnectionGenerate;
import ru.itprogram.utils.generater.FileReaderGenerate;
import ru.itprogram.utils.generater.dao.*;
import ru.itprogram.utils.generater.dto.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

@Configuration
@PropertySource("classpath:database.properties")
public class SpringConfig {

    @Value("${jdbc.url}")
    private String dbUrl;

    @Value("${jdbc.username}")
    private String dbUserName;

    @Value("${jdbc.password}")
    private String dbPassword;

    @Bean
    public FileReaderGenerate getFileReaderGenerater() {
        return new FileReaderGenerate() {
            @Override
            public FileReader createFileReader() throws FileNotFoundException {
                return new FileReader(ReadProductInFile.PATH);
            }
        };
    }

    @Bean
    public CurrentConnectionGenerate getCurrentConnectionGenerate() {
        return new CurrentConnectionGenerate() {
            @Override
            public CurrentConnection createCurrentConnection() {
                return new CurrentConnection(dbUrl, dbUserName, dbPassword);
            }
        };
    }

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
    public PurchaseHistoryProvider getPurchaseHistoryProvider(UserDto userDto) {
        return new PurchaseHistoryProvider(userDto);
    }

    @Bean
    public BrandRepository getBrandRepository() {
        return new BrandRepository();
    }

    @Bean
    public OrderRepository getOrderRepository() {
        return new OrderRepository();
    }

    @Bean
    public ProductRepository getProductRepository() {
        return new ProductRepository();
    }

    @Bean
    public ProductTypeRepository getProductTypeRepository() {
        return new ProductTypeRepository();
    }

    @Bean
    public PromoCodeRepository getPromoCodeRepository() {
        return new PromoCodeRepository();
    }

    @Bean
    public UserRepository getUserRepository() {
        return new UserRepository();
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
    @Scope("prototype")
    public Brand getBrand() {
        return new Brand();
    }

    @Bean
    @Scope("prototype")
    public Order getOrder() {
        return new Order();
    }

    @Bean
    @Scope("prototype")
    public Product getProduct() {
        return new Product();
    }

    @Bean
    @Scope("prototype")
    public ProductType getProductType() {
        return new ProductType();
    }

    @Bean
    @Scope("prototype")
    public PromoCode getPromoCode() {
        return new PromoCode();
    }

    @Bean
    @Scope("prototype")
    public User getUser() {
        return new User();
    }

    @Bean
    public UserNotFoundException getUserNotFoundException() {
        return new UserNotFoundException(LoginProvider.USER_NOT_FOUND);
    }

    @Bean
    public UserNameIsRepeatedException getUserNameIsRepeatedException() {
        return new UserNameIsRepeatedException(RegistrationProvider.USER_NAME_IS_REPEATED);
    }

    @Bean
    public StringBuilder getStringBuilder() {
        return new StringBuilder();
    }

    @Bean
    public OrderDtoGenerate orderDtoGenerate() {
        return new OrderDtoGenerate() {
            @Override
            public OrderDto createOrderDto() {
                return new OrderDto();
            }
        };
    }

    @Bean
    public ArrayListGenerate getArrayListGenerate() {
        return new ArrayListGenerate() {
            @Override
            public ArrayList createArrayList() {
                return new ArrayList<>();
            }
        };
    }

    @Bean
    public UserDtoGenerate getUserDtoGenerate() {
        return new UserDtoGenerate() {
            @Override
            public UserDto createUserDto() {
                return new UserDto();
            }
        };
    }

    @Bean
    public BrandDtoGenerate getBrandDtoGenerate() {
        return new BrandDtoGenerate() {
            @Override
            public BrandDto createBrandDto() {
                return new BrandDto();
            }
        };
    }

    @Bean
    public ProductDtoGenerate getProductDtoGenerate() {
        return new ProductDtoGenerate() {
            @Override
            public ProductDto createProductDto() {
                return new ProductDto();
            }
        };
    }

    @Bean
    public ProductTypeDtoGenerate getProductTypeDtoGenerate() {
        return new ProductTypeDtoGenerate() {
            @Override
            public ProductTypeDto createProductTypeDto() {
                return new ProductTypeDto();
            }
        };
    }

    @Bean
    public PromoCodeDtoGenerate getPromoCodeDtoGenerate() {
        return new PromoCodeDtoGenerate() {
            @Override
            public PromoCodeDto createPromoCodeDto() {
                return new PromoCodeDto();
            }
        };
    }

    @Bean
    public BrandGenerate getBrandGenerate() {
        return new BrandGenerate() {
            @Override
            public Brand createBrand() {
                return new Brand();
            }
        };
    }

    @Bean
    public OrderGenerate getOrderGenerate() {
        return new OrderGenerate() {
            @Override
            public Order createOrder() {
                return new Order();
            }
        };
    }

    @Bean
    public ProductGenerate getProductGenerate() {
        return new ProductGenerate() {
            @Override
            public Product createProduct() {
                return new Product();
            }
        };
    }

    @Bean
    public ProductTypeGenerate getProductTypeGenerate() {
        return new ProductTypeGenerate() {
            @Override
            public ProductType createProductType() {
                return new ProductType();
            }
        };
    }

    @Bean
    public PromoCodeGenerate getPromoCodeGenerate() {
        return new PromoCodeGenerate() {
            @Override
            public PromoCode createPromoCode() {
                return new PromoCode();
            }
        };
    }

    @Bean
    public UserGenerate getUserGenerate() {
        return new UserGenerate() {
            @Override
            public User createUser() {
                return new User();
            }
        };
    }

    @Bean
    public BrandDtoToBrand getBrandDtoToBrand() {
        return new BrandDtoToBrand();
    }

    @Bean
    public BrandToBrandDto getBrandToBrandDto() {
        return new BrandToBrandDto();
    }

    @Bean
    public OrderDtoToOrder getOrderDtoToOrder() {
        return new OrderDtoToOrder();
    }

    @Bean
    public OrderToOrderDto getOrderToOrderDto() {
        return new OrderToOrderDto();
    }

    @Bean
    public ProductDtoToProduct getProductDtoToProduct() {
        return new ProductDtoToProduct();
    }

    @Bean
    public ProductToProductDto getProductToProductDto() {
        return new ProductToProductDto();
    }

    @Bean
    public ProductTypeDtoToProductType getProductTypeDtoToProductType() {
        return new ProductTypeDtoToProductType();
    }

    @Bean
    public ProductTypeToProductTypeDto getProductTypeToProductTypeDto() {
        return new ProductTypeToProductTypeDto();
    }

    @Bean
    public PromoCodeDtoToPromoCode getPromoCodeDtoToPromoCode() {
        return new PromoCodeDtoToPromoCode();
    }

    @Bean
    public PromoCodeToPromoCodeDto getPromoCodeToPromoCodeDto() {
        return new PromoCodeToPromoCodeDto();
    }

    @Bean
    public UserDtoToUser getUserDtoToUser() {
        return new UserDtoToUser();
    }

    @Bean
    public UserToUserDto getUserToUserDto() {
        return new UserToUserDto();
    }
}
