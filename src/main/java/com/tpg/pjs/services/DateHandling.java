package com.tpg.pjs.services;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public interface DateHandling {

    DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("dd/MM/yyyy HH:mm:ss")
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    default ZonedDateTime toZonedDateTime(String value) {
        return ZonedDateTime.parse(value, DATE_TIME_FORMATTER);
    }
}
