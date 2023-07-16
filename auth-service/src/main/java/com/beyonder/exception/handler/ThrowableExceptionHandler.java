package com.beyonder.exception.handler;

import com.beyonder.dto.baseresponse.ExceptionDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ThrowableExceptionHandler implements ExceptionMapper<Throwable> {

    private static final Logger log = LoggerFactory.getLogger(ThrowableExceptionHandler.class);

    @Override
    public Response toResponse(Throwable throwable) {
        log.error("auth-Service", throwable);
        Throwable rootCause = getRootCause(throwable);

        ExceptionDTO exceptionDTO = new ExceptionDTO(500, throwable.getMessage());

        if (rootCause instanceof PSQLException && rootCause.getMessage().contains("already exists")) {
            exceptionDTO.setCode(400);
            exceptionDTO.setMessage("The provided username has already been used. Please use a different username.");
            return Response.status(Response.Status.BAD_REQUEST).entity(exceptionDTO).build();
        }

        return Response.serverError().entity(exceptionDTO).build();
    }

    private Throwable getRootCause(Throwable throwable) {
        if (throwable.getCause() != null) {
            return getRootCause(throwable.getCause());
        }
        return throwable;
    }
}
