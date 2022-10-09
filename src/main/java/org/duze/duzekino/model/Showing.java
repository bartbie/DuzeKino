package org.duze.duzekino.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Cascade;

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

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="movieId")
    @NotNull @NonNull
    private Movie movie;

    @ManyToOne()
    @JoinColumn(name="theaterId")
    @NotNull @NonNull
    private Theater theater;

    @OneToMany(mappedBy = "showing")
    private Set<Booking> bookings = new HashSet<>();


    public Showing(@NonNull LocalDateTime time, @NonNull Movie movie, @NonNull Theater theater) {
        this.time = time;
        this.movie = movie;
        this.theater = theater;
    }
}
