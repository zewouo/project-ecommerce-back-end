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
public class ProductNotFoundException extends RuntimeException implements GraphQLError {

    private final Map<String, Object> extensions = new HashMap<>();



    public ProductNotFoundException(String message, Integer id) {
        super(message);
        extensions.put("invalidOrderId", id);
    }

    public ProductNotFoundException(String message, String name) {
        super(message);
        extensions.put("invalidProductName", name);
    }

    public ProductNotFoundException( String message) {
        super(message);
        extensions.put("invalidProductName",null);
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