package com.senla.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/** @author Aliaksei Kaspiarovich */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "communities")
public class Community {

    @Id @GeneratedValue private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_communities",
            joinColumns = {@JoinColumn(name = "community_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"community_id", "user_id"})})
    private Set<User> followers = new HashSet<>();
}
