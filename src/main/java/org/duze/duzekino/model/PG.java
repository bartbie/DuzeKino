package org.duze.duzekino.model;

import lombok.NonNull;

public enum PG {
    ANY(0),
    THIRTEEN(13),
    FIFTEEN(15),
    SEVENTEEN(17);

    private final int number;

    PG(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static PG toClosest(@NonNull Integer integer) {
        if (integer >= 17) {
            return PG.SEVENTEEN;
        } else if (integer >= 15) {
            return PG.FIFTEEN;
        } else if (integer >= 13) {
            return PG.THIRTEEN;
        } else {
            return PG.ANY;
        }
    }
}
