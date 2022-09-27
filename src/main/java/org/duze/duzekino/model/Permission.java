package org.duze.duzekino.model;

/**
 * enum representation
 */
public enum Permission {
    ADMIN(100),
    BASIC(0);

    private final Integer level;

    Permission(Integer level) {
        this.level = level;
    }


    public Integer getLevel() {
        return level;
    }

}
