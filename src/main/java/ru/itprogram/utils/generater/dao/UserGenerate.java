package ru.itprogram.utils.generater.dao;

import ru.itprogram.entity.dao.User;

public abstract class UserGenerate {
    public User getUser() {
        return createUser();
    }

    public abstract User createUser();
}
