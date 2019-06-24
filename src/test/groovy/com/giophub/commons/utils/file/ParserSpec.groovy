package com.giophub.commons.utils.file

import com.giophub.commons.utils.xml.Parser
import org.w3c.dom.Document
import spock.lang.Specification



class ParserSpec extends Specification {

    def "Load a file as String and read the first line"() {
        given:
        FileLoader fl = new FileLoader()
        String textFile = fl.load("static/xml/xml-file.xml").asString()
        String firstLine = textFile.readLines()[0]

        expect:
        firstLine == "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
    }

    def "Load a file as String and parse it"() {
        given:
        FileLoader fl = new FileLoader()
        String textFile = fl.load("static/xml/xml-file.xml").asString()

        String expectedFile =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<note>\n" +
                        "    <to>Tove</to>\n" +
                        "    <from>Jani</from>\n" +
                        "    <heading>Reminder</heading>\n" +
                        "    <body>Don't forget me this weekend!</body>\n" +
                        "</note>"

        expect:
        textFile.trim() == expectedFile.trim()
    }

    def "Load an xml file and pretty print it on the console"() {
        given:
        FileLoader fl = new FileLoader()
        String xmlText = fl.load("static/xml/xml-file.xml").asString()
        Document xml = Parser.convertToDOM(xmlText)
        Parser.prettyPrintXml(xml)

        expect: true
    }

    def "Load xml request file and pretty print it on the console"() {
        given:
        FileLoader fl = new FileLoader()
        String xmlText = fl.load("static/xml/xml-file-inline.xml").asString()
        Document xml = Parser.convertToDOM(xmlText)
        Parser.prettyPrintXml(xml)

        expect: true
    }
}