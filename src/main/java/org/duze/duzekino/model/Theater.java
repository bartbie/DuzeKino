package org.duze.duzekino.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public final class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @NotNull
    private Long theaterId;

    @NotNull @NonNull private String name;

    public Theater(@NonNull String name) {
        this.name = name;
    }

/*    @OneToMany (mappedBy = "theater")
    private Set<Showing> showings = new HashSet<>();

    @OneToMany (mappedBy = "theater")
    private Set<Seat> seats = new HashSet<>();*/

}
