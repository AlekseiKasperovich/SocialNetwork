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
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User author;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_events", joinColumns = {
            @JoinColumn(name = "event_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")},
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"event_id", "user_id"})})
    private Set<User> participants = new HashSet<>();
}
