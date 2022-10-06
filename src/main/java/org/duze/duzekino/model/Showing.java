package org.duze.duzekino.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public final class Showing {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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








}
