package com.giophub.rest.jersey.service.exception;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JerseyServiceCustomException extends Exception {

    private final static Logger LOG = LoggerFactory.getLogger(JerseyServiceCustomException.class);

    @Getter
    private String code;
    private String message = getMessage();


    public JerseyServiceCustomException(String message) {
        super(message);
    }

    public JerseyServiceCustomException(String message, String code){
        super(message);
        this.code = code;
        LOG.debug(toString());
    }

    @Override
    public String toString() {
        return "Fields{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
