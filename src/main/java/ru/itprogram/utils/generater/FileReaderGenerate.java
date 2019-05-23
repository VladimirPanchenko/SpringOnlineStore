package ru.itprogram.utils.generater;

import java.io.FileNotFoundException;
import java.io.FileReader;

public abstract class FileReaderGenerate {
    public FileReader getFileReader() throws FileNotFoundException {
        return createFileReader();
    }

    public abstract FileReader createFileReader() throws FileNotFoundException;
}
