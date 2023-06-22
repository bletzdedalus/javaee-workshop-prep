package com.dedalus.error;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;

@Provider
public class InvalidAnimalTypeExceptionMapper implements ExceptionMapper<InvalidAnimalTypeException> {

    @Override
    public Response toResponse(InvalidAnimalTypeException ex) {
        ArrayList<ErrorResponse> errors = new ArrayList<>();
        errors.add(new ErrorResponse(422, ex.getMessage(), "common.wrong.animaltype"));
        return Response.status(422).entity(errors).type(MediaType.APPLICATION_JSON).build();
    }
}
