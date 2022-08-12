package com.senla.model;

import com.senla.dto.constants.Roles;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/** @author Aliaksei Kaspiarovich */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "roles")
public class Role {

    @Id @GeneratedValue private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(unique = true, nullable = false, updatable = false)
    private Roles name;

    public Role(Roles name) {
        this.name = name;
    }
}
