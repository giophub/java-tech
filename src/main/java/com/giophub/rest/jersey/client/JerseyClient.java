package com.giophub.rest.jersey.client;

import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class JerseyClient {

    private static final Logger LOG = LoggerFactory.getLogger(JerseyClient.class);

    private static final String REST_PATH = "rest";
    private static final String JERSEY_SERVICE_PATH = "jersey-service";
    private static final String TODO_SERVICE_PATH = "todo";
    private WebTarget target;

    public JerseyClient() {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(
                UriBuilder.fromUri("http://localhost:8080/").build());
        this.target = target;

        // get responses from services
        jerseyServiceResponse();
        todoResourceResponse();
    }

    public void jerseyServiceResponse() {
        String response =
                target.
                        path(REST_PATH).path(JERSEY_SERVICE_PATH).
                        request().
                        accept(MediaType.TEXT_PLAIN).
                        get(Response.class).
                        toString();
        LOG.info(response);

        String plainAnswer =
                target.
                        path(REST_PATH).path(JERSEY_SERVICE_PATH).
                        request().
                        accept(MediaType.TEXT_PLAIN).
                        get(String.class);
        LOG.info(plainAnswer);

        String xmlAnswer =
                target.
                        path(REST_PATH).path(JERSEY_SERVICE_PATH).
                        request().
                        accept(MediaType.TEXT_XML).
                        get(String.class);
        LOG.info(xmlAnswer);

        String htmlAnswer =
                target.
                        path(REST_PATH).path(JERSEY_SERVICE_PATH).
                        request().
                        accept(MediaType.TEXT_HTML).
                        get(String.class);
        LOG.info(htmlAnswer);
    }

    public void todoResourceResponse() {
        // GET XML
        String xmlResponse =
                target.
                        path(REST_PATH).path(TODO_SERVICE_PATH).
                        request().
                        accept(MediaType.TEXT_XML).
                        get(String.class);
        LOG.info(xmlResponse);

        // GET XML from application
        String xmlAppResponse =
                target.
                        path(REST_PATH).path(TODO_SERVICE_PATH).
                        request().
                        accept(MediaType.APPLICATION_XML).
                        get(String.class);
        LOG.info(xmlAppResponse);

        // GET JSON from application
        String jsonAppResponse =
                target.
                        path(REST_PATH).path(TODO_SERVICE_PATH).
                        request().
                        accept(MediaType.APPLICATION_JSON).
                        get(String.class);
        LOG.info(jsonAppResponse);
    }
}
