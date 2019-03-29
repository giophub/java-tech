package com.giophub.rest.jersey.service;

import com.giophub.rest.jersey.service.exception.JerseyServiceCustomException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/jersey-service")
public class JerseyService {

    private static final Logger LOG = LoggerFactory.getLogger(JerseyService.class);


    public JerseyService() {
        LOG.info("Jersey REST service published at: http://localhost:8080/rest/jersey-service");
    }

    // This method is called if TEXT_PLAIN is requested
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "Hello Jersey";
    }

    // This method is called if XML is requested
    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
    }

    // This method is called if HTML is requested
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html>" + "<title>" + "Hello Jersey" + "</title>"
                + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
    }

    // This method is called if XML is requested, making an automatic mapping from JAXB annotated class to XML
    @GET
    @Produces(MediaType.TEXT_XML + ";charset=UTF-8")
    @Path("/advanced")
    public Todo advanced() {
        LOG.info("Requested the advanced resource as HTML");
        Todo todo = new Todo();
        todo.setSummary("XML Todo Summary");
        todo.setDescription("XML Todo Description");
        return todo;
    }

    // This method throws an EXCEPTION in JSON
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/customException/{code}/{message}/")
    public String customException(
            @PathParam("code")      String code,
            @PathParam("message")   String message
    ) throws JerseyServiceCustomException {
        LOG.info("Requested the customException resource as JSON");

        Gson json = new GsonBuilder().create();

        String result = "{" + json.toJson("Assignment") + ":{" +
                json.toJson("status")  + ":" + json.toJson(code) + ", " +
                json.toJson("message") + ":" + json.toJson(message) + "}" +
                        "}";
        LOG.info("Arguments converted in JSON: {}", result);

        throw new JerseyServiceCustomException(message, code);
    }

}
