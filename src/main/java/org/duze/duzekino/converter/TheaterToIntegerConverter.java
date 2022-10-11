package org.duze.duzekino.converter;

import org.duze.duzekino.model.PG;
import org.duze.duzekino.model.Theater;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TheaterToIntegerConverter implements AttributeConverter<Theater, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Theater theater) {
        return theater == null
                ? null
                : theater.getNumber();
    }

    @Override
    public Theater convertToEntityAttribute(Integer integer) throws IllegalStateException {
        return integer == null
                ? null
                : Theater.toTheater(integer);
    }
}
