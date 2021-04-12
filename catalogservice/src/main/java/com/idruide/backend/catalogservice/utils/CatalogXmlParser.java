package com.idruide.backend.catalogservice.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class CatalogXmlParser {


    private static XmlMapper xmlMapper = new XmlMapper();
    static {
        xmlMapper.registerModule(new JSR310Module());
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public static XmlMapper getXmlMapper() {
       xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
       xmlMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        return xmlMapper;
    }





}
