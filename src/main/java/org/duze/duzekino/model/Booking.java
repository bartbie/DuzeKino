package org.duze.duzekino.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long booking_Id;


//    @ManyToOne
//    @JoinColumn(name = "showing_id")
//    private Showing showing;

    private int customer_id;
    private int nrOfSeats;
}
