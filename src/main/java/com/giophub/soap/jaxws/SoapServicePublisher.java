package com.giophub.soap.jaxws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Endpoint;
import java.io.IOException;

public class SoapServicePublisher
        extends HttpServlet {

    private final Logger LOG = LoggerFactory.getLogger(SoapServicePublisher.class);

    public SoapServicePublisher() {
        super();

        /*LOG.info("Publishing the SOAP service");
        Endpoint.publish("http://localhost:8080/JaxWsSoapService", new SoapServiceImpl());*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("Publishing the SOAP service");
        Endpoint.publish("http://localhost:8081/JaxWsSoapService", new SoapServiceImpl());
    }
}
