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
    private static final String SERVICE_PATH = "jersey-service";

    public JerseyClient() {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(
                UriBuilder.fromUri("http://localhost:8080/").build());

        String response =
                target.
                        path(REST_PATH).path(SERVICE_PATH).
                        request().
                        accept(MediaType.TEXT_PLAIN).
                        get(Response.class).
                        toString();

        String plainAnswer =
                target.
                        path(REST_PATH).path(SERVICE_PATH).
                        request().
                        accept(MediaType.TEXT_PLAIN).
                        get(String.class);

        String xmlAnswer =
                target.
                        path(REST_PATH).path(SERVICE_PATH).
                        request().
                        accept(MediaType.TEXT_XML).
                        get(String.class);

        String htmlAnswer =
                target.
                        path(REST_PATH).path(SERVICE_PATH).
                        request().
                        accept(MediaType.TEXT_HTML).
                        get(String.class);

        LOG.info(response);
        LOG.info(plainAnswer);
        LOG.info(xmlAnswer);
        LOG.info(htmlAnswer);
    }
}
