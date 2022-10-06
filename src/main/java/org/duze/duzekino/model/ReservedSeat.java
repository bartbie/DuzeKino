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

    @NotNull @NonNull private int bookingId;

    @OneToOne() //not sure of this
    @JoinColumn(name="seatId")
    @NotNull @NonNull private Seat seatId;

    @ManyToOne() // not sure of this
    @JoinColumn(name="id")
    @NotNull @NonNull private Showing showingId;

}
