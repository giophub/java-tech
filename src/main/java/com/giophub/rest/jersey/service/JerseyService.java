package com.giophub.rest.jersey.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/jersey-service")
public class JerseyService {

    private static final Logger LOG = LoggerFactory.getLogger(JerseyService.class);

    public JerseyService() {
        LOG.info("Jersey REST service published at: http://localhost:8080/rest/jersey-service");
    }

    // This method is called if TEXT_PLAIN is request
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "Hello Jersey";
    }

    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
    }

    // This method is called if HTML is request
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html>" + "<title>" + "Hello Jersey" + "</title>"
                + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
    }

    // This method is called if TEXT_PLAIN is request
    /*@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayJsonHello() {
        return "Hello Jersey";
    }*/

}
