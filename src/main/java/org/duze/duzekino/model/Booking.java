package org.duze.duzekino.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public final class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @NotNull
    private Long bookingId;

    @NotNull @NonNull private String firstName;
    @NotNull @NonNull private String lastName;
    @NotNull @NonNull private String phoneNumber;
    @NotNull @NonNull private String email;

    @OneToOne()
    @JoinColumn(name="showingId")
    @NotNull @NonNull
    private Showing showing;

    @OneToMany()
    @JoinColumn(name="reservedId")
    @NotNull @NonNull
    private List<ReservedSeat> reservedSeat;

    public Booking(@NonNull String firstName, @NonNull String lastName, @NonNull String phoneNumber, @NonNull String email, @NonNull Showing showing, @NonNull List<ReservedSeat> reservedSeat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.showing = showing;
        this.reservedSeat = reservedSeat;
    }
}
