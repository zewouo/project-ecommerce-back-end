package com.idruide.backend.orderservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.idruide.backend.orderservice.dto.OrderDto;

public class OrderXmlParser {


    private static XmlMapper xmlMapper = new XmlMapper();
    static {
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public static String writeValueAsString(final OrderDto obj) throws JsonProcessingException {
        return xmlMapper.writeValueAsString(obj);
    }


}
