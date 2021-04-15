package com.idruide.backend.packingservice.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Thierry Kwekam
 */
public class PackingDateUtils {

    public  static final String datePattern = "dd-MM-yyyy HH:mm";
    private static final int deliveryDate = 5;

    public static LocalDateTime SystemDateNow() {
        return LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern(datePattern)), DateTimeFormatter.ofPattern(datePattern));
    }

    public static LocalDateTime estimatedDeliveryDate() {
        return LocalDateTime.parse(LocalDateTime.now().plusDays(deliveryDate).format(DateTimeFormatter.ofPattern(datePattern)), DateTimeFormatter.ofPattern(datePattern));
    }

}
