package com.system.booking.movie.MovieBooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private int movie_id;
    private String movie_name;
    private LocalDateTime start_time;
    private Set<Integer> screen_id;
}
