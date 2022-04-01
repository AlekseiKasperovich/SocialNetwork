package com.senla.model;

import com.senla.api.dto.сonstants.Gender;
import com.senla.api.dto.сonstants.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Aliaksei Kaspiarovich
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    @Enumerated(value = EnumType.STRING)
    private Gender sex;

    private String phone;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
