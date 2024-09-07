package com.system.booking.movie.MovieBooking.service;

import com.system.booking.movie.MovieBooking.entity.BookTicket;
import com.system.booking.movie.MovieBooking.entity.Movie;
import com.system.booking.movie.MovieBooking.entity.Screen;
import com.system.booking.movie.MovieBooking.entity.Seat;
import com.system.booking.movie.MovieBooking.repository.BookTicketRepository;
import com.system.booking.movie.MovieBooking.repository.MovieRepository;
import com.system.booking.movie.MovieBooking.repository.ScreenRepository;
import com.system.booking.movie.MovieBooking.repository.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookTicketService {
    @Autowired
    BookTicketRepository bookTicketRepository;
    @Autowired
    ScreenRepository screenRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    SeatRepository seatRepository;

    @Transactional
    public void bookTicket(BookTicket bookTicket) {
        Screen screen = screenRepository.findById(bookTicket.getScreen().getScreen_id())
                .orElseThrow(() -> new RuntimeException("Screen not found"));
        Movie movie = movieRepository.findById(bookTicket.getMovie().getMovie_id())
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        List<Seat> seatList = new ArrayList<>();
        for(Seat seat : bookTicket.getSeats()) {
            Seat seatInDb = seatRepository.findById(seat.getSeat_id())
                    .orElseThrow(() -> new RuntimeException("Seat not found"));
            // Check if the seat is already booked
            if ("booked".equals(seatInDb.getStatus())) {
                throw new RuntimeException("Seat with ID " + seat.getSeat_id() + " is already booked.");
            }
            seatInDb.setStatus("booked");
            seatInDb.setBookTicket(bookTicket);
            seatRepository.save(seatInDb);
            seatList.add(seatInDb);
        }
        bookTicket.setSeats(seatList);
        bookTicket.setScreen(screen);
        bookTicket.setMovie(movie);
        bookTicketRepository.save(bookTicket);
    }
}
