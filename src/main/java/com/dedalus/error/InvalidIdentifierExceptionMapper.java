package com.dedalus.error;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;

@Provider
public class InvalidIdentifierExceptionMapper implements ExceptionMapper<InvalidIdentifierException> {

    @Override
    public Response toResponse(InvalidIdentifierException ex) {
        ArrayList<ErrorResponse> errors = new ArrayList<>();
        errors.add(new ErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(), ex.getMessage(), "common.invalid.identifier"));
        return Response.status(Response.Status.BAD_REQUEST).entity(errors).type(MediaType.APPLICATION_JSON).build();
    }
}
