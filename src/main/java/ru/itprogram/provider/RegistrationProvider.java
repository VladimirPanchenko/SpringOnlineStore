package ru.itprogram.provider;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dto.UserDto;
import ru.itprogram.exceptions.UserNameIsRepeatedException;
import ru.itprogram.service.UserService;
import ru.itprogram.utils.generater.dto.UserDtoGenerate;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

public class RegistrationProvider {
    public static final String USER_NAME_IS_REPEATED = "Такое имя не допустимо";
    @Autowired
    private UserService userService;
    @Autowired
    private UserDtoGenerate userDtoGenerate;
    @Autowired
    private UserNameIsRepeatedException userNameIsRepeatedException;
    private List<UserDto> userDtoList;

    @PostConstruct
    private void init() {
        userDtoList = userService.getAll();
    }

    public void addUser(String name, String email, String phone, String password)
            throws UserNameIsRepeatedException {
        if (!checkUserName(name)) {
            UserDto userDto = userDtoGenerate.getUserDto();
            userDto.setId(0);
            userDto.setAdministrator(false);
            userDto.setName(name);
            userDto.setEmail(email);
            userDto.setPhone(phone);
            userDto.setPassword(password);
            userDto.setDateTimeRegistration(LocalDateTime.now());
            userService.add(userDto);
        }
    }

    private boolean checkUserName(String name) throws UserNameIsRepeatedException {
        boolean isRepeated = false;
        for (UserDto userDto : userDtoList) {
            if (userDto.getName().equals(name)) {
                isRepeated = true;
                throw userNameIsRepeatedException;
            }
        }
        return isRepeated;
    }
}
