package com.system.booking.movie.MovieBooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatDto {
    private int seat_id;
    private String status;
    private int screen_id;
    private int theatre_id;
}
