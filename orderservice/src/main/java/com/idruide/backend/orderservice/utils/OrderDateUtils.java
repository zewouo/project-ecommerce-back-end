package com.idruide.backend.orderservice.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderDateUtils {

    private static String datePattern = "dd-MM-yyyy HH:mm";
    private static int deliveryDate = 5;

    public static LocalDateTime SystemDateNow() {
        return LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern(datePattern)), DateTimeFormatter.ofPattern(datePattern));
    }

    public static LocalDateTime estimatedDeliveryDate() {
        return LocalDateTime.parse(LocalDateTime.now().plusDays(deliveryDate).format(DateTimeFormatter.ofPattern(datePattern)), DateTimeFormatter.ofPattern(datePattern));

    }

}
