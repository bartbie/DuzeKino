package org.duze.duzekino.model;

import lombok.*;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @NotNull @NonNull private double totalPrice;

    @ManyToOne()
    @JoinColumn(name="id")
    @NotNull @NonNull
    private Showing showing;

/*    @OneToMany (mappedBy = "booking", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ReservedSeat> reservedSeats = new HashSet<>();*/


    public Booking(@NonNull String firstName, @NonNull String lastName, @NonNull String phoneNumber, @NonNull String email, @NonNull Showing showing, @NonNull double totalPrice) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.showing = showing;
//        this.reservedSeats = reservedSeats;
        this.totalPrice = totalPrice;
    }
}
