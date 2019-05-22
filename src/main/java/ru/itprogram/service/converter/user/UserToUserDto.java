package ru.itprogram.service.converter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itprogram.entity.dao.User;
import ru.itprogram.entity.dto.UserDto;
import ru.itprogram.service.converter.Converter;
import ru.itprogram.utils.generater.dto.UserDtoGenerate;

@Component
public class UserToUserDto implements Converter<UserDto, User> {
    @Autowired
    private UserDtoGenerate userDtoGenerate;

    @Override
    public UserDto conversion(User user) {
        UserDto userDto = userDtoGenerate.getUserDto();
        userDto.setId(user.getId());
        userDto.setAdministrator(user.isAdministrator());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setPassword(user.getPassword());
        userDto.setDateTimeRegistration(user.getDateTimeRegistration());
        return userDto;
    }
}
