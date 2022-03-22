package com.senla.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events_messages")
public class EventMessage {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDateTime posted;

    @Column(nullable = false)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Event event;

}
