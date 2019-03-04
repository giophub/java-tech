package com.giophub.soap.jaxws.client;

import com.giophub.soap.jaxws.client.stub.*;
import org.apache.commons.io.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SoapClient {

    public static final Logger LOG = LoggerFactory.getLogger(SoapClient.class);

    public SoapClient() {
        LOG.info("Making the SOAP call using stubs.");
        doCallUsingStub();

        LOG.info("Making the SOAP call using a generic SOAP connection and the ObjectFactory stub.");
        doCallUsingSoapConnection();
    }

    private void doCallUsingStub() {
        SoapServiceImplService serviceImpl = new SoapServiceImplService();
        SoapService ws = serviceImpl.getSoapServiceImplPort();
        String helloWorldResponse =  ws.helloWorld();
        LOG.info("helloWorldResponse: {}", helloWorldResponse);
        String sayHelloResponse = ws.sayHello("Mike");
        LOG.info("sayHelloResponse: {}", sayHelloResponse);
    }

    private void doCallUsingSoapConnection() {
        String endpoint = "http://localhost:8081/JaxWsSoapService";
        String soapAction = "";
        String soapRequest = "";

        ObjectFactory factory = new ObjectFactory();
        JAXBElement<HelloWorld> helloWorld = factory.createHelloWorld(new HelloWorld());
        JAXBElement<SayHello> sayHello = factory.createSayHello(new SayHello().withArg0("Hello Armand!"));

        SOAPConnection conn = null;
        SOAPMessage response = null;
        try {
            SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
            SOAPPart soapPart = soapMessage.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            SOAPBody body = envelope.getBody();

            SOAPBodyElement bodyElement = (SOAPBodyElement) body.addChildElement(helloWorld.getName());
            soapMessage.saveChanges();
            this.logSoapMessage(soapMessage);

            conn = SOAPConnectionFactory.newInstance().createConnection();
            response = conn.call(soapMessage, endpoint);
            this.logSoapMessage(response);


            SOAPMessage soapMessage2 = MessageFactory.newInstance().createMessage();
            SOAPPart soapPart2 = soapMessage2.getSOAPPart();
            SOAPEnvelope envelope2 = soapPart2.getEnvelope();
            SOAPBody body2 = envelope2.getBody();
            // WARNING: this instruction does not convert the object in the right soap request,
            // indeed the argument is null, rather than to be "Hello Armand!".
            SOAPBodyElement bodyElement2 = (SOAPBodyElement) body2.addChildElement(sayHello.getName());
            soapMessage2.saveChanges();
            this.logSoapMessage(soapMessage2);

            conn = SOAPConnectionFactory.newInstance().createConnection();
            response = conn.call(soapMessage2, endpoint);
            this.logSoapMessage(response);

        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    private void logSoapMessage(SOAPMessage response) {
        OutputStream writer = new ByteArrayOutputStream();
        try {
            response.writeTo(writer);
            String result = ((ByteArrayOutputStream) writer).toString(String.valueOf(Charsets.UTF_8));
            LOG.info(result);
        } catch (IOException | SOAPException e) {
            e.printStackTrace();
        }
    }

}
