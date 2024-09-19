package com.system.booking.movie.MovieBooking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movie_id;

    @NotBlank(message = "Movie name cannot be blank")
    @NotNull(message = "Movie name cannot be null")
    private String movie_name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start_time;

    @ManyToMany
    @JoinTable(
            name = "movie_screen",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "screen_id")
    )
    private Set<Screen> screens;
}
