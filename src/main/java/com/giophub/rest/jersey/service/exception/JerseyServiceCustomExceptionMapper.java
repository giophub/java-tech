package com.giophub.rest.jersey.service.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JerseyServiceCustomExceptionMapper implements ExceptionMapper<JerseyServiceCustomException> {

    @Override
    public Response toResponse(JerseyServiceCustomException e) {

        return Response.status(400)
                .entity(new ErrorMessage(e))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
