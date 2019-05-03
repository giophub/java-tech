package com.giophub.design.pattern.strategy


import spock.lang.Specification

class ClientTest extends Specification {

    def "Given the context, set the ZIP compression strategy"() {
        given:
        CompressionContext ctx = new CompressionContext();
        ctx.setStrategy(new ZipCompressionStrategy());

        List<File> fileList = Arrays.asList(new File("file1"), new File("file2"));
        ctx.createArchive(fileList as ArrayList<File>);
    }

    def "Given the context, set the RAR compression strategy"() {
        given:
        CompressionContext ctx = new CompressionContext();
        ctx.setStrategy(new RarCompressionStrategy());

        List<File> fileList = Arrays.asList(new File("file1"), new File("file2"));
        ctx.createArchive(fileList as ArrayList<File>);
    }


    def "test the client for the Strategy pattern"() {
        given:
        Client client = new Client();
    }

}