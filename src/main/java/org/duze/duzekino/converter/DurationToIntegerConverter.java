package org.duze.duzekino.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;

@Converter
public final class DurationToIntegerConverter implements AttributeConverter<Duration, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Duration duration) {
        return duration == null
                ? null
                : Math.toIntExact(duration.toMinutes());
    }

    @Override
    public Duration convertToEntityAttribute(Integer integer) {
        return integer == null
                ? null
                : Duration.ofMinutes(integer);
    }
}
