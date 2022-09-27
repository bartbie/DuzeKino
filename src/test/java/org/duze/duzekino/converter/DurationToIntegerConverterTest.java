package org.duze.duzekino.converter;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DurationToIntegerConverterTest {

    final DurationToIntegerConverter converter = new DurationToIntegerConverter();

    @Test
    void convertToDatabaseColumn() {
        int minutes = 80;
        assertEquals(minutes, converter.convertToDatabaseColumn(Duration.ofMinutes(minutes)));
    }

    @Test
    void convertToEntityAttribute() {
        int mins = 160;
        Duration length = Duration.ofMinutes(mins);
        assertEquals(length, converter.convertToEntityAttribute(mins));

    }
}