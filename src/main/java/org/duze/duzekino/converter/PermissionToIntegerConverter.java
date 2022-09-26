package org.duze.duzekino.converter;

import org.duze.duzekino.model.Permission;
import org.springframework.lang.NonNull;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PermissionToIntegerConverter implements AttributeConverter<Permission, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Permission permission) {
        return permission == null ? null : permission.getLevel();
    }

    @Override
    public Permission convertToEntityAttribute(Integer integer) {
        return integer == null ? null : toClosest(integer);
    }

    public Permission toClosest(@NonNull Integer integer) {
        return integer >= 100 ? Permission.ADMIN : Permission.BASIC;
    }
}
