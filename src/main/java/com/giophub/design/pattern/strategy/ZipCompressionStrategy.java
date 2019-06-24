package com.giophub.design.pattern.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;

public class ZipCompressionStrategy implements CompressionStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(ZipCompressionStrategy.class);

    @Override
    public void compressFiles(ArrayList<File> fileList) {
        // using zip approach
        LOG.info("using ZIP compression strategy");
    }
}
