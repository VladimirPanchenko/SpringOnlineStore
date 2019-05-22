package ru.itprogram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itprogram.config.SpringConfig;
import ru.itprogram.entity.dto.UserDto;
import ru.itprogram.exceptions.UserNameIsRepeatedException;
import ru.itprogram.exceptions.UserNotFoundException;
import ru.itprogram.provider.*;
import ru.itprogram.utils.ReadProductInFile;
import ru.itprogram.view.*;

import java.util.Scanner;

public class App {
    public static final String ERROR_COMMAND = "Вы ввели невеную команду!";
    protected static Logger logger;
    private static UserDto userDto;
    private static BasketProvider basketProvider;
    private static AnnotationConfigApplicationContext context;

    public static void main(String[] args) {
        context = new AnnotationConfigApplicationContext(SpringConfig.class);
        logger = LoggerFactory.getLogger(App.class);
        basketProvider = context.getBean(BasketProvider.class);
        while (true) {
            if (userDto != null) {
                entrance(userDto.getName(), userDto.getPassword());
                MenuLoginView.start(userDto.getName());
            } else {
                start();
            }
        }
    }

    public static void start() {
        MenuView.welcomePrint();
        switch (insertInt()) {
            case 1:
                MenuLoginView.welcomePrint();
                entrance(insertString(), insertString());
                break;
            case 2:
                MenuRegistrationView.welcome();
                registration();
                break;
            default:
                System.out.println(ERROR_COMMAND);
                break;
        }
    }

    public static void entrance(String name, String password) {
        try {
            LoginProvider loginProvider = context.getBean(LoginProvider.class, name, password);
                userDto = loginProvider.entrance();
        } catch (UserNotFoundException e) {
            logger.warn(e.getMessage(), e);
        }
        if (userDto != null) {
            if (!userDto.isAdministrator()) {
                store();
            } else {
                entranceAdmin();
            }
        }
    }

    public static void store() {
        MenuLoginView.subMenu();
        switch (insertInt()) {
            case 1:
                MenuProductView
                        .productPrint(context.getBean(CatalogProvider.class).getProductDtoList());
                MenuProductView.addProductPrint();
                int temp = -1;
                while (temp != 0) {
                    temp = insertInt();
                    MenuProductView
                            .productPrint(context.getBean(CatalogProvider.class)
                                    .getProductDtoList());
                    MenuProductView.addProductPrint();
                    basketProvider.addProduct(temp, userDto);
                }
                break;
            case 2:
                MenuHistoryView
                        .HistoryPrint(context.getBean(PurchaseHistoryProvider.class).getOrderDtoList(),
                                userDto);
                break;
            case 3:
                MenuBasketView.basketPrint(basketProvider);
                MenuBasketView.totalPricePrint(basketProvider.allPrice());
                basket();
                break;
            default:
                System.out.println(ERROR_COMMAND);
                break;
        }
    }

    public static void registration() {
        try {
            context.getBean(RegistrationProvider.class)
                    .addUser(insertString(), insertString(), insertString(), insertString());
        } catch (UserNameIsRepeatedException e) {
            logger.warn(e.getMessage(), e);
        }
    }

    public static void entranceAdmin() {
        MenuAdministratorView.welcome();
        if (insertInt() == 1) {
            context.getBean(ReadProductInFile.class).readFile(ReadProductInFile.PATH);
        } else {
            System.exit(0);
        }
    }

    public static void basket() {
        MenuBasketView.basketSubMenuPrint();
        switch (insertInt()) {
            case -1:
                break;
            case 0:
                basketProvider.buy();
                MenuBasketView.thanks();
                break;
            case 3:
                MenuBasketView.removeProduct();
                int temp = -1;
                while (temp != 0) {
                    MenuBasketView.basketPrint(basketProvider);
                    temp = insertInt();
                    basketProvider.RemoveProduct(temp, userDto);
                }
                break;
        }
    }

    public static int insertInt() {
        return context.getBean(Scanner.class).nextInt();
    }

    public static String insertString() {
        return context.getBean(Scanner.class).next();
    }
}
