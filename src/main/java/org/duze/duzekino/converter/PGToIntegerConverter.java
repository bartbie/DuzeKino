package org.duze.duzekino.converter;


import lombok.NonNull;
import org.duze.duzekino.model.PG;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class PGToIntegerConverter implements AttributeConverter<PG, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PG pg) {
        return pg == null
                ? null
                : pg.getNumber();
    }

    @Override
    public PG convertToEntityAttribute(Integer integer) {
        return integer == null
                ? null
                : toClosest(integer);
    }

    public PG toClosest(@NonNull Integer integer) {
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
