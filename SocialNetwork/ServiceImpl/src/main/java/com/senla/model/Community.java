package com.senla.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

/**
 * @author Aliaksei Kaspiarovich
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "communities")
public class Community {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(orphanRemoval = true)
    @JoinTable(name = "users_communities", joinColumns = {
            @JoinColumn(name = "community_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")},
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"community_id", "user_id"})})
    private Set<User> followers = new java.util.LinkedHashSet<>();
}
