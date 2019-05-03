package com.giophub.commons.utils.xml;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.jdom2.input.DOMBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.*;
import java.util.Objects;

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
            DocumentBuilder builder = newDocumentBuilderInstance();
            dom = builder.parse(is);
            dom.getDocumentElement().normalize(); // normalize the document
        } catch (SAXException e) {
            LOGGER.error("Error on parsing the XML Document from Input Stream: {}", e.getMessage());
        } catch (IOException e) {
            LOGGER.error("I/O Exception on parsing XML Document: {}", e.getMessage());
        }
        return dom;
    }

    public static Document convertToDOM(JAXBElement jaxbElement) {
        return convertToDOM(jaxbElement, true, true);
    }
    public static Document convertToDOM(JAXBElement jaxbElement, boolean addXmlHeader, boolean indent) {
        Document dom = newDocumentBuilderInstance().newDocument();
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(jaxbElement.getDeclaredType());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("jaxb.fragment", addXmlHeader); // to stop <?xml ... being added ?>
            marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, indent );
            marshaller.marshal(jaxbElement, dom);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return dom;
    }

    public static org.jdom2.Document convertToJdom(Document document) {
        DOMBuilder builder = new DOMBuilder();
        return builder.build(document);
    }

    public static void prettyPrintXml(Document document) {
        LOGGER.info("Pretty print xml\n{}", prettyPrintXml( convertToJdom(document) ));
    }
    public static String prettyPrintXml(org.jdom2.Document document) {
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        return xmlOutputter.outputString(document);
    }

    public static String format(String xml, Boolean ommitXmlDeclaration) {
        String result = null;

        DocumentBuilder db = newDocumentBuilderInstance();
        StringReader sr = new StringReader(xml);
        InputSource is = new InputSource(sr);
        Writer outxml = new StringWriter();
        try {
            Document doc = db.parse(is);

            OutputFormat format = new OutputFormat(doc);
            format.setIndenting(true);
            format.setIndent(2);
            format.setOmitXMLDeclaration(ommitXmlDeclaration);
            format.setLineWidth(Integer.MAX_VALUE);

            XMLSerializer serializer = new XMLSerializer(outxml, format);
            serializer.serialize(doc);

            result = outxml.toString();
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        } finally {
            try { outxml.close(); } catch (IOException e) { e.printStackTrace(); }
            sr.close();
        }
        return result;
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

    public static String asString(BufferedReader bufferedReader) {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            if (!bufferedReader.ready())
                LOGGER.warn("The buffer reader is null or it is not ready !");

            while (null != (line = bufferedReader.readLine())) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            LOGGER.error("Error on reading buffer: {}", e.getMessage());
        }
        return sb.toString();
    }

    public static String toString(SOAPMessage soapMessage) {
        if (soapMessage == null)
            LOGGER.error("Cannot convert the soap message to string, its value is null");

        String result = null;
        try {
            ByteArrayOutputStream writer = new ByteArrayOutputStream();
            Objects.requireNonNull(soapMessage).writeTo(writer);
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            result = xmlOutputter.outputString(
                    new DOMBuilder().build(convertToDOM(writer.toString("UTF8"))));
        } catch (SOAPException | IOException e) {
            e.printStackTrace();
        }

        if (result == null)
            LOGGER.error("Cannot convert the soap message to string, its value is null");

        return result;
    }

    public void asInputStream(InputStreamReader inputStreamReader) {
//        while (null != (line = FileInputStream(File file)))
    }

    // ---------------------------- PRIVATE ----------------------

    private static DocumentBuilder newDocumentBuilderInstance() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        dbf.setIgnoringComments(false);
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setNamespaceAware(true);
        // dbf.setCoalescing(true);
        // dbf.setExpandEntityReferences(true);

        DocumentBuilder builder = null;
        try {
            builder = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("Document builder factory exception: {}", e);
        }
        return builder;
    }
}
