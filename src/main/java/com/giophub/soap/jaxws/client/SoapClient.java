package com.giophub.soap.jaxws.client;

import com.giophub.commons.utils.xml.Parser;
import com.giophub.soap.jaxws.client.stub.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.soap.*;

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
        String helloWorldResponse = ws.helloWorld();
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
            LOG.info(Parser.toString(soapMessage));

            conn = SOAPConnectionFactory.newInstance().createConnection();
            response = conn.call(soapMessage, endpoint);
            LOG.info(Parser.toString(response));


            conn = SOAPConnectionFactory.newInstance().createConnection();
            response = conn.call(
                    buildSoapMessage(sayHello), endpoint);
            LOG.info(Parser.toString(response));

        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    private SOAPMessage buildSoapMessage(JAXBElement payload) throws SOAPException {
        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPBody body = envelope.getBody();
        body.addDocument(Parser.convertToDOM(payload));
        soapMessage.saveChanges();
        LOG.info(Parser.toString(soapMessage));
        return soapMessage;
    }

}
