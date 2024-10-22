package com.system.booking.movie.MovieBooking.service;

import com.system.booking.movie.MovieBooking.dto.SeatDto;
import com.system.booking.movie.MovieBooking.entity.Seat;
import com.system.booking.movie.MovieBooking.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {
    @Autowired
    SeatRepository seatRepository;
    public List<SeatDto> fetchAllSeatsOfScreen(int screen_id) {
        List<Seat> seats = seatRepository.findByScreen_ScreenId(screen_id);
        return seats.stream().map(seat ->
                        SeatDto.builder()
                                .seat_id(seat.getSeat_id())
                                .status(seat.getStatus())
                                .screen_id(seat.getScreen().getScreen_id())
                                .theatre_id(seat.getScreen().getTheatre().getTheatre_id())
                                .build())
                .collect(Collectors.toUnmodifiableList());

    }
}
