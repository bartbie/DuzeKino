package org.duze.duzekino.model;

import lombok.*;
import org.duze.duzekino.converter.PGToIntegerConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public final class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @NotNull private Long id;

    @NotNull @NonNull private String title;
    @NotNull @NonNull private String description;
    @NotNull @NonNull private Integer year;
    @NotNull @NonNull private Duration length;

    @Convert(converter = PGToIntegerConverter.class)
    @NotNull private PG rating;

    public Movie(
            @NonNull String title,
            @NonNull String description,
            @NonNull Integer year,
            @NonNull Duration length,
            @NonNull PG rating) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.length = length;
        this.rating = rating;
    }
}
