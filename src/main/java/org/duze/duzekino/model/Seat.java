package org.duze.duzekino.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @NonNull private Long seatId;

    @NotNull @NonNull private int row;
    @NotNull @NonNull private int number;

    @ManyToOne()
    @JoinColumn(name="theaterId")
    @NotNull @NonNull private Theater theater;
}
