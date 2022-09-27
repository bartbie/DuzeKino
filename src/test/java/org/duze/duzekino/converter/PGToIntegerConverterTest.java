package org.duze.duzekino.converter;

import org.duze.duzekino.model.PG;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PGToIntegerConverterTest {
    final PGToIntegerConverter converter = new PGToIntegerConverter();

    @Test
    void convertToDatabaseColumn() {
        int age = 15;
        assertEquals(age, converter.convertToDatabaseColumn(PG.FIFTEEN));
    }

    @Test
    void convertToEntityAttribute() {
        PG rating = PG.SEVENTEEN;
        assertEquals(rating, converter.convertToEntityAttribute(17));
    }
}