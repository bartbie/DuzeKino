package org.duze.duzekino.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class ReservedSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @NotNull private Long reservedId;

    @OneToOne()
    @JoinColumn(name="seatId")
    @NotNull @NonNull private Seat seat;

    public ReservedSeat(@NonNull Seat seat) {
        this.seat = seat;
    }
}
