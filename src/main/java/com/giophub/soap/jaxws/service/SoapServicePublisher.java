package com.giophub.soap.jaxws.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.xml.ws.Endpoint;

public class SoapServicePublisher
        extends HttpServlet {

    private final Logger LOG = LoggerFactory.getLogger(SoapServicePublisher.class);

    public SoapServicePublisher() {
        super();

        String endpoint = "http://localhost:8081/JaxWsSoapService";
        Endpoint.publish(endpoint, new SoapServiceImpl());
        LOG.info("Published the SOAP service at: {}", endpoint + "?wsdl");
    }
}
