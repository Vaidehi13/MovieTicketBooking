package com.system.booking.movie.MovieBooking.repository;

import com.system.booking.movie.MovieBooking.entity.BookTicket;
import com.system.booking.movie.MovieBooking.entity.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookTicketRepository extends JpaRepository<BookTicket, Integer> {
    List<BookTicket> findByStatus(TicketStatus status);
    List<BookTicket> findByUser_Id(int userId);
}
