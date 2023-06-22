package com.dedalus.error;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;

@Provider
public class AdoptionExceptionMapper implements ExceptionMapper<AlreadyAdoptedException> {



    @Override
    public Response toResponse(AlreadyAdoptedException ex) {
        ArrayList<ErrorResponse> errors = new ArrayList<>();
        errors.add(new ErrorResponse(Status.BAD_REQUEST.getStatusCode(), ex.getMessage(), "common.already.adopted"));
        return Response.status(Status.BAD_REQUEST).entity(errors).type(MediaType.APPLICATION_JSON).build();
    }

}
