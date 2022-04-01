package com.senla.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_communities", joinColumns = {
            @JoinColumn(name = "community_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")},
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"community_id", "user_id"})})
    private Set<User> followers = new HashSet<>();
}
