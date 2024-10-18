package com.system.booking.movie.MovieBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class BookTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int booking_id;
    @ManyToOne // Assuming each ticket is associated with one user
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;
    @OneToMany(mappedBy = "bookTicket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    private float amount;
    private String status;
}
