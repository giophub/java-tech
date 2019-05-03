package com.giophub.design.pattern.strategy;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;

/**
 * Our context will provide a way for the client to compress the files.
 * Let's say that there is a preferences setting in our application that sets which compression algorithm to use.
 * We can change our strategy using the setCompressionStrategy method in the Context. @see the Client class.
 */
public class CompressionContext {

    // this can be set a runtime by the application preferences with the setter method
    @Getter @Setter
    private CompressionStrategy strategy;

    // use the strategy
    public void createArchive(ArrayList<File> fileList) {
        strategy.compressFiles(fileList);
    }
}
