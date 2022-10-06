package org.duze.duzekino.service;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;


public final class TestUtils {
    private TestUtils() {}

    /**
     * takes entity, clears the database, and saves the entity to it
     * @param entity Spring {@code @Entity} object
     * @param entityRepo Spring {@code JpaRepository} repo
     * @param <T> class of {@code entity}
     * @return same {@code entity} provided in parameters
     */
    public static <T> T setUpRepo(@NonNull T entity, @NonNull JpaRepository<T, ?> entityRepo) {
        entityRepo.deleteAll();
        entityRepo.save(entity);
        return entity;
    }
}
