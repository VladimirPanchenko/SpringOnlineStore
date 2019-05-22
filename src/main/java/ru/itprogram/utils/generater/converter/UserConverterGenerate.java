package ru.itprogram.utils.generater.converter;

import ru.itprogram.service.converter.user.UserToUserDto;

public abstract class UserConverterGenerate {
    public UserToUserDto getUserToUserDto() {
        return createUserToUserDto();
    }

    public abstract UserToUserDto createUserToUserDto();
}
