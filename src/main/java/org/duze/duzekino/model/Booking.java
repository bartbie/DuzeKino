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


}
