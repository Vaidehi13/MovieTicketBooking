package com.system.booking.movie.MovieBooking.dto;

import com.system.booking.movie.MovieBooking.entity.TicketStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookTicketDto {
    private int booking_id;
    private int user_id;
    private int screen_id;
    private List<Integer> seat_id;
    private int movie_id;
    private float amount;
    private TicketStatus status;
}
