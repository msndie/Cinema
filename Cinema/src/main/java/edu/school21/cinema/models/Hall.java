package edu.school21.cinema.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "serial_number")
    private Long serialNumber;
    @Column(name = "number_of_seats")
    private int numberOfSeats;
    @ToString.Exclude
    @OneToMany(mappedBy = "hall")
    private Set<Session> sessions = new LinkedHashSet<>();
}
