package com.giophub.design.pattern.strategy;

import java.io.File;
import java.util.ArrayList;

public class Client {

    Client() {
        CompressionContext ctx = new CompressionContext();
        // we could assume context is already set by preferences
        ctx.setStrategy(new ZipCompressionStrategy());

        ArrayList<File> fileList = new ArrayList<>();
        fileList.add(new File("file1"));
        fileList.add(new File("file2"));

        // get a list of files
        ctx.createArchive(fileList);
    }
}
