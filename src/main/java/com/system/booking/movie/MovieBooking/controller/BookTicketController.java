package com.system.booking.movie.MovieBooking.controller;

import com.system.booking.movie.MovieBooking.dto.BookTicketDto;
import com.system.booking.movie.MovieBooking.entity.BookTicket;
import com.system.booking.movie.MovieBooking.entity.Seat;
import com.system.booking.movie.MovieBooking.entity.User;
import com.system.booking.movie.MovieBooking.exception.ResourceNotFoundException;
import com.system.booking.movie.MovieBooking.repository.BookTicketRepository;
import com.system.booking.movie.MovieBooking.repository.UserRepository;
import com.system.booking.movie.MovieBooking.service.BookTicketService;
import com.system.booking.movie.MovieBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/book")
@RestController
public class BookTicketController {
    @Autowired
    BookTicketService bookTicketService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookTicketRepository bookTicketRepository;
    @PostMapping("/ticket")
    public ResponseEntity<?> bookTicket(@RequestBody BookTicket bookTicket) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        bookTicket.setUser(user);
        bookTicketService.bookTicket(bookTicket);
        return ResponseEntity.ok("Ticket booked successfully");
    }
    @PatchMapping("/cancel")
    public ResponseEntity<?> cancelTicket(@RequestBody BookTicket bookTicket ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        bookTicket.setUser(user);
        bookTicketService.cancelTicket(bookTicket);
        return ResponseEntity.ok("Ticket cancelled successfully");
    }
    public List<Integer> getSeatIds(BookTicket bookTicket){
        return bookTicket.getSeats().stream()
                .map(Seat::getSeat_id)
                .collect(Collectors.toUnmodifiableList());
    }
    @GetMapping("/allTickets")
    public ResponseEntity<?> getAllBookingsOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        List<BookTicket> bookTickets = bookTicketRepository.findByUser_Id(user.getId());
        if(bookTickets != null && !bookTickets.isEmpty()){
            List<BookTicketDto> bookTicketDto = bookTickets.stream().map(
                    bookTicket -> BookTicketDto.builder()
                            .booking_id(bookTicket.getBooking_id())
                            .movie_id(bookTicket.getMovie().getMovie_id())
                            .amount(bookTicket.getAmount())
                            .user_id(bookTicket.getUser().getId())
                            .screen_id(bookTicket.getScreen().getScreen_id())
                            .seat_id(getSeatIds(bookTicket))
                            .status(bookTicket.getStatus())
                            .build()
            ).collect(Collectors.toUnmodifiableList());
        return new ResponseEntity<>(bookTicketDto, HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("No bookings found");
    }
}
