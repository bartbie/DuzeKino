package org.duze.duzekino.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class PGTest {

    @Test
    void toClosestBiggest() {
        int age = 18;
        assertEquals(PG.SEVENTEEN, PG.toClosest(age));
    }

    @Test
    void toCLosestSmallest() {
        int age = 1;
        assertEquals(PG.ANY, PG.toClosest(age));
    }

    @Test
    void toClosestMiddle() {
        int age = 14;
        assertEquals(PG.THIRTEEN, PG.toClosest(age));
    }
}