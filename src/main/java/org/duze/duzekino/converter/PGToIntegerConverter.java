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
                : PG.toClosest(integer);
    }


}
