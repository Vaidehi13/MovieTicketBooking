package com.system.booking.movie.MovieBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seat_id;
    @NotBlank(message = "Seat status cannot be blank")
    private String status;
    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookTicket bookTicket;

    public Seat(String available, Screen screen) {
        this.status = available;
        this.screen = screen;
    }
}
