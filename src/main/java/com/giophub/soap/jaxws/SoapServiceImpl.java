package com.giophub.soap.jaxws;

import com.giophub.soap.jaxws.contract.SoapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebService;

@WebService(endpointInterface = "com.giophub.soap.jaxws.contract.SoapService")
public class SoapServiceImpl
        implements SoapService {

    private final Logger LOG = LoggerFactory.getLogger(SoapServiceImpl.class);

    @Override
    public String HelloWorld() {
        String message = "Hello world!";
        LOG.info("Message: {}", message);
        return message;
    }

    @Override
    public String sayHello(String name) {
        String message = "Hello " + name;
        LOG.info("Message: {}", message);
        return message;
    }
}
