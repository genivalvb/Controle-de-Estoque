package com.controle_estoqueapi.Controle.de.Estoque.Utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ConversorDate {

    private static final ZoneId zoneId = ZoneId.systemDefault();

    public static LocalDateTime toLocalDateTime(Instant instant){
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static Instant toInstant(LocalDateTime localDateTime){
        return localDateTime.atZone(zoneId).toInstant();
    }
}
