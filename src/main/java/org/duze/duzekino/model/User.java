package org.duze.duzekino.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 32, name = "username")
    private String username;

    @Column(nullable = false, unique = false, length = 255, name = "password")
    private String password;

    @Column(nullable = false, unique = false, length = 255, name = "email")
    private String email;

}
