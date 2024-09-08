package com.system.booking.movie.MovieBooking.controller;

import com.system.booking.movie.MovieBooking.entity.BookTicket;
import com.system.booking.movie.MovieBooking.service.BookTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/book")
@RestController
public class BookTicketController {
    @Autowired
    BookTicketService bookTicketService;
    @PostMapping("/ticket")
    public ResponseEntity<?> bookTicket(@RequestBody BookTicket bookTicket) {
        bookTicketService.bookTicket(bookTicket);
        return ResponseEntity.ok("Ticket booked successfully");
    }
    @PatchMapping("/cancel")
    public ResponseEntity<?> cancelTicket(@RequestBody BookTicket bookTicket ) {
        bookTicketService.cancelTicket(bookTicket);
        return ResponseEntity.ok("Ticket cancelled successfully");
    }
}
