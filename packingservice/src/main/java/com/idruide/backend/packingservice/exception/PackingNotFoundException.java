package com.idruide.backend.packingservice.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Thierry Kwekam
 */
public class PackingNotFoundException extends RuntimeException implements GraphQLError {

    private final Map<String, Object> extensions = new HashMap<>();

    public PackingNotFoundException(String message, Integer id) {
        super(message);
        extensions.put("invalidOrderId", id);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}