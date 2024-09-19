package com.system.booking.movie.MovieBooking.service;

import com.system.booking.movie.MovieBooking.entity.BookTicket;
import com.system.booking.movie.MovieBooking.entity.Movie;
import com.system.booking.movie.MovieBooking.entity.Screen;
import com.system.booking.movie.MovieBooking.entity.Seat;
import com.system.booking.movie.MovieBooking.exception.InvalidResource;
import com.system.booking.movie.MovieBooking.exception.ResourceNotFoundException;
import com.system.booking.movie.MovieBooking.repository.BookTicketRepository;
import com.system.booking.movie.MovieBooking.repository.MovieRepository;
import com.system.booking.movie.MovieBooking.repository.ScreenRepository;
import com.system.booking.movie.MovieBooking.repository.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
                .orElseThrow(() -> new ResourceNotFoundException("Screen not found"));
        Movie movie = movieRepository.findById(bookTicket.getMovie().getMovie_id())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        List<Seat> seatList = new ArrayList<>();
        for(Seat seat : bookTicket.getSeats()) {
            Seat seatInDb = seatRepository.findById(seat.getSeat_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Seat not found"));
            // Check if the seat is already booked
            if ("booked".equals(seatInDb.getStatus())) {
                throw new InvalidResource("Seat with ID " + seat.getSeat_id() + " is already booked.");
            }
            seatInDb.setStatus("booked");
            seatInDb.setBookTicket(bookTicket);
            seatRepository.save(seatInDb);
            seatList.add(seatInDb);
        }
        bookTicket.setSeats(seatList);
        bookTicket.setScreen(screen);
        bookTicket.setMovie(movie);
        bookTicket.setStatus("Active");
        bookTicketRepository.save(bookTicket);
    }
    @Scheduled(fixedRate = 3600000)
    public void updateExpiredTickets(){
        List<BookTicket> bookTickets = bookTicketRepository.findByStatus("active");
        LocalDateTime now = LocalDateTime.now();
        for(BookTicket bookTicket : bookTickets) {
            if(bookTicket.getMovie().getStart_time().isBefore(now)){
                bookTicket.setStatus("Expired");
                bookTicketRepository.save(bookTicket);
            }
        }
    }

    @Transactional
    public void cancelTicket(BookTicket bookTicket) {
        BookTicket bookTicketInDb = bookTicketRepository.findById(bookTicket.getBooking_id())
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        List<Seat> seatList = seatRepository.findByBookingId(bookTicket.getBooking_id());
        if(bookTicketInDb.getStatus().equals("Active")) {
            bookTicketInDb.setStatus("Cancelled");
            bookTicketRepository.save(bookTicketInDb);
            for(Seat seat : seatList) {
                if(seat.getStatus().equals("booked")) {
                    seat.setStatus("Cancelled");
                    seatRepository.save(seat);
                }else throw new InvalidResource("Seat with ID " + seat.getSeat_id() + " is invalid.");
            }
        }else{
            throw new RuntimeException("Booking already expired.");
        }
    }
}
