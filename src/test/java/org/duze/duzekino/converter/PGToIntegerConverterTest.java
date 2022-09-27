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

    @Test
    void toClosestBiggest() {
        int age = 18;
        assertEquals(PG.SEVENTEEN, converter.toClosest(18));
    }

    @Test
    void toCLosestSmallest() {
        int age = 1;
        assertEquals(PG.ANY, converter.toClosest(age));
    }

    @Test
    void toClosestMiddle() {
        int age = 14;
        assertEquals(PG.THIRTEEN, converter.toClosest(age));
    }
}