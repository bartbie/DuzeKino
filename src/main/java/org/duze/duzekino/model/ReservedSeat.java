package org.duze.duzekino.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

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

    @ManyToOne()
    @JoinColumn (name = "bookingId")
    private Booking booking;

    public ReservedSeat(@NonNull Seat seat) {
        this.seat = seat;
    }
}
