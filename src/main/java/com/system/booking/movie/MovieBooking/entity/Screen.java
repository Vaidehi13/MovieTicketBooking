package com.system.booking.movie.MovieBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Seats cannot be null")
    @NotNull(message = "Seats cannot be blank")
    @Min(value = 1,message = "Minimum seats should be one")
    private int total_seats;
}
