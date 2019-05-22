package ru.itprogram.utils.generater.dto;

import ru.itprogram.entity.dto.UserDto;

public abstract class UserDtoGenerate {
    public UserDto getUserDto() {
        return createUserDto();
    }

    public abstract UserDto createUserDto();
}
