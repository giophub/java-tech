package com.giophub.soap.jaxws.service;

import com.giophub.soap.jaxws.service.contract.SoapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "com.giophub.soap.jaxws.service.contract.SoapService")
public class SoapServiceImpl
        implements SoapService {

    private final Logger LOG = LoggerFactory.getLogger(SoapServiceImpl.class);

    @Override
    public String helloWorld() {
        String message = "Hello world!";
        LOG.info("Message: {}", message);
        return message;
    }

    /**
     * The @WebParam annotation is necessary as java interfaces do not store the Parameter name in the .class file.
     * So if you leave out the annotation your parameter will be named arg0.
     *
     * @param name
     * @return a message
     */
    @Override
    public String sayHello(
            @WebParam(name = "name") String name) {
        String message = "Hello " + name;
        LOG.info("Message: {}", message);
        return message;
    }
}
