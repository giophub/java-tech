package com.giophub.commons.utils.file

import spock.lang.Specification


class FileLoaderSpec extends Specification {

    def "Load file from resource class path"() {
        given:
        FileLoader fl = new FileLoader()
        String textFile = fl.load("static/text-file.txt").asString()
        String firstLine = textFile.readLines()[0]

        expect:
        firstLine == "Hello world with fileLoader"
    }
}
