package org.duze.duzekino.model;

public enum PG {
    ANY(0),
    THIRTEEN(13),
    FIFTEEN(15),
    SEVENTEEN(17);

    private final int number;

    PG(int number) { this.number = number; }
    public int getNumber() {return number;}

}
