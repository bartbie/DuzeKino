package org.duze.duzekino.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String email;

    @OneToOne()
    @JoinColumn(name = "scheduleId")
    private Schedule schedule;

}