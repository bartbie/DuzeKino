package org.duze.duzekino.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.duze.duzekino.converter.TheaterToIntegerConverter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public final class Showing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @NotNull
    private Long id;

    @NotNull @NonNull private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name="movieId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Movie movie;

    @NotNull @NonNull
    @Convert(converter = TheaterToIntegerConverter.class)
    private Theater theater;


    public Showing(@NonNull LocalDateTime time, @NonNull Movie movie, @NonNull Theater theater) {
        this.time = time;
        this.movie = movie;
        this.theater = theater;
    }
}
