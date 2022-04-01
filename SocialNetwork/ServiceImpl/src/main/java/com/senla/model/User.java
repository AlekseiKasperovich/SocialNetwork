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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
