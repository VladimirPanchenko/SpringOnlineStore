package ru.itprogram.utils.generater;

import ru.itprogram.utils.CurrentConnection;

public abstract class CurrentConnectionGenerate {
    public CurrentConnection getCurrentConnection() {
        return createCurrentConnection();
    }

    public abstract CurrentConnection createCurrentConnection();
}
