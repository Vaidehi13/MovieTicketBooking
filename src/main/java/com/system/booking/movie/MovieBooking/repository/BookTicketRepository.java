package com.system.booking.movie.MovieBooking.repository;

import com.system.booking.movie.MovieBooking.entity.BookTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTicketRepository extends JpaRepository<BookTicket, Integer> {
}
