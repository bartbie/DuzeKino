package org.duze.duzekino.model;

import lombok.*;
import org.duze.duzekino.converter.PermissionToIntegerConverter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true, length = 32, name = "username")
    private String username;

    @Column(nullable = false, unique = false, length = 255, name = "password")
    private String password;

    @Column(nullable = false, unique = false, length = 255, name = "email")
    private String email;

    @Convert(converter = PermissionToIntegerConverter.class)
    private Permission permission;

    public User(String username, String password, String email, Permission permission) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.permission = permission;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

}
