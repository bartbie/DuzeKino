package org.duze.duzekino.model;

public enum Theater {
    THEATER1(1),
    THEATER2(2);

    private final int number;

    Theater(int number) {
        this.number = number;
    }

    public int getNumber() { return this.number; }

    public static Theater toTheater(int integer) {
        return switch (integer) {
            case 1 -> THEATER1;
            case 2 -> THEATER2;
            default -> throw new IllegalStateException("Such Theater does not exist");
        };
    }

}
