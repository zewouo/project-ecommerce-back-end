package com.idruide.backend.orderservice.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author Thierry Kwekam
 */
public class OrderNotFoundException extends RuntimeException implements GraphQLError {

    private final Map<String, Object> extensions = new HashMap<>();


    public OrderNotFoundException(String message, Integer id) {
        super(message);
        extensions.put("invalidOrderId", id);
    }

    public OrderNotFoundException(String message, String name) {
        super(message);
        extensions.put("invalidOrderName", name);
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