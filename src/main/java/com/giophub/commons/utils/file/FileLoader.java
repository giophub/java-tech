package com.giophub.commons.utils.file;

import com.giophub.commons.utils.xml.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

// todo : this class should return only a File object
public class FileLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileLoader.class);

    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private File input;
    private InputStream is;


    public FileLoader load(String name) {
        ClassLoader classLoader = getClass().getClassLoader();
        name = classLoader.getResource(name).getFile();

//        FileReader fileReader = null;
        try {
            input = new File(name);
            fileReader = new FileReader(name);
            is = new FileInputStream(input);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            LOGGER.error("Error on loading file: " + name + "\n" + e.getMessage());
        }
        return this;
    }

    public void flush() {

    }

    public void close() {
        try {
            if (bufferedReader != null) bufferedReader.close();
            if (fileReader != null) fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    TODO write the content of file in memory to re-use it n-times.
    public void inMemory() {

    }

    public FileReader asFileReader() {
        return fileReader;
    }

    public InputStream asInputStream() {
        return is;
    }

    public BufferedReader asBufferedReader() {
        return bufferedReader;
    }

    public String asString() {
        String result = Parser.asString(bufferedReader);
        close();
        return result;
    }



    public File getInput() {
        return input;
    }

}
