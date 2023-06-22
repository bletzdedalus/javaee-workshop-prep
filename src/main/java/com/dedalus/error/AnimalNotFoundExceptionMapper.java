package com.dedalus.error;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;

@Provider
public class AnimalNotFoundExceptionMapper implements ExceptionMapper<AnimalNotFoundException> {

    @Override
    public Response toResponse(AnimalNotFoundException ex) {
        ArrayList<ErrorResponse> errors = new ArrayList<>();
        errors.add(new ErrorResponse(Status.NOT_FOUND.getStatusCode(), ex.getMessage(), "common.animal.notFound"));
        return Response.status(Status.NOT_FOUND).entity(errors).type(MediaType.APPLICATION_JSON).build();
    }
}
