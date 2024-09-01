package com.system.booking.movie.MovieBooking.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int screen_id;
    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;
    private int total_seats;
}
