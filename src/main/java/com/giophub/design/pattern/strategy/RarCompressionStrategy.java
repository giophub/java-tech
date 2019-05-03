package com.giophub.design.pattern.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;

public class RarCompressionStrategy implements CompressionStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(RarCompressionStrategy.class);

    @Override
    public void compressFiles(ArrayList<File> fileList) {
        // using RAR approach
        LOG.info("using RAR compression strategy");
    }
}
