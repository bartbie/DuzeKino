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
@AllArgsConstructor
public final class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @NotNull private Long id;

    @NotNull private String title;
    @NotNull private String description;
    @NotNull private Integer year;
    @NotNull private Duration length;
    @Convert(converter = PGToIntegerConverter.class)
    @NotNull private PG rating;

    public Movie(@NonNull String title, String description, Integer year, Duration length, PG rating) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.length = length;
        this.rating = rating;
    }
}
