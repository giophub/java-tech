package com.giophub.main;

import com.giophub.rest.jersey.client.JerseyClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;



@EnableScheduling
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String argv[]) {
        // check input arguments

        JerseyClient restClient = new JerseyClient();

    }

}
