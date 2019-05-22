package ru.itprogram.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.User;
import ru.itprogram.entity.dto.UserDto;
import ru.itprogram.repository.UserRepository;
import ru.itprogram.service.converter.user.UserDtoToUser;
import ru.itprogram.service.converter.user.UserToUserDto;
import ru.itprogram.utils.generater.ArrayListGenerate;

import java.util.List;

public class UserService implements Service<UserDto> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArrayListGenerate arrayListGenerate;
    @Autowired
    private UserToUserDto userToUserDto;
    @Autowired
    private UserDtoToUser userDtoToUser;

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.getAllEntity();
        List<UserDto> userDtoList = arrayListGenerate.getArrayList();
        for (User user : users) {
            userDtoList.add(userToUserDto.conversion(user));
        }
        return userDtoList;
    }

    @Override
    public void add(UserDto userDto) {
        userRepository.saveEntity(userDtoToUser.conversion(userDto));
    }
}
