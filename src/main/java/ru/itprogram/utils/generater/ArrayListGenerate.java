package ru.itprogram.utils.generater;

import java.util.ArrayList;

public abstract class ArrayListGenerate {
    public ArrayList getArrayList() {
        return createArrayList();
    }

    public abstract ArrayList createArrayList();
}
