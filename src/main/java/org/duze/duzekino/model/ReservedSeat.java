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

    @ManyToOne()
    @JoinColumn(name="bookingId")
    @NotNull @NonNull private Booking booking;

    @OneToOne()
    @JoinColumn(name="seatId")
    @NotNull @NonNull private Seat seatId;

}
