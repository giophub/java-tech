package com.giophub.commons.utils.xml;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class Parser {
    private static final Logger LOGGER = LoggerFactory.getLogger(Parser.class);

    public void Parser() {
    }

    public static Document convertToDOM(String message) {
        return convertToDOM( IOUtils.toInputStream(message, Charsets.UTF_8) );
    }

    public static Document convertToDOM(InputStream is) {
        Document dom = null;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            dbf.setIgnoringComments(false);
            dbf.setIgnoringElementContentWhitespace(true);
            dbf.setNamespaceAware(true);
            // dbf.setCoalescing(true);
            // dbf.setExpandEntityReferences(true);

            DocumentBuilder builder = dbf.newDocumentBuilder();
            dom = builder.parse(is);
            dom.getDocumentElement().normalize(); // normalize the document

        } catch (ParserConfigurationException e) {
            LOGGER.error("Document builder factory exception: {}", e.getMessage());
        } catch (SAXException e) {
            LOGGER.error("Error on parsing the XML Document from Input Stream: {}", e.getMessage());
        } catch (IOException e) {
            LOGGER.error("I/O Exception on parsing XML Document: {}", e.getMessage());
        }
        return dom;
    }

    public static String prettyPrintXml(Document dom) {
        DOMSource source = new DOMSource(dom);
        return prettyPrintXml(source);
    }
    public static String prettyPrintXml(Source source) {
        String result;
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            transformer.transform(source, xmlOutput);

            result = xmlOutput.getWriter().toString();
            LOGGER.info("Pretty print:\n{}", result);
            return result;
        } catch (Exception e) {
            LOGGER.error("Cannot execute the pretty print operation: {}", e);
            return null;
        }
    }

    public void asBufferedReader(BufferedReader bufferedReader) {
        LOGGER.debug("Reading file as buffered reader");
        String line = null;
        try {
            if (!bufferedReader.ready())
                LOGGER.warn("The buffer reader is null or it is not ready !");

            while (null != (line = bufferedReader.readLine())) {
                System.out.println(line);
            }
        } catch (IOException e) {
            LOGGER.error("Error on reading buffer\n" + e.getMessage());
        }
    }

    public String asString(BufferedReader bufferedReader) {
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            if (!bufferedReader.ready())
                LOGGER.warn("The buffer reader is null or it is not ready !");

            while (null != (line = bufferedReader.readLine())) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            LOGGER.error("Error on reading buffer\n" + e.getMessage());
        }

        return sb.toString();
    }

    public void asInputStream(InputStreamReader inputStreamReader) {
//        while (null != (line = FileInputStream(File file)))
    }
}
