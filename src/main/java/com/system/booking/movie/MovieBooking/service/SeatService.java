package com.system.booking.movie.MovieBooking.service;

import com.system.booking.movie.MovieBooking.entity.Seat;
import com.system.booking.movie.MovieBooking.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    SeatRepository seatRepository;
    public List<Seat> fetchAllSeatsOfScreen(int screen_id) {
        return seatRepository.findByScreen_ScreenId(screen_id);
    }
}
