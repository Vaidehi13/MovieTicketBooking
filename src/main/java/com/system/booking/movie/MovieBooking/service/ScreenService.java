package com.system.booking.movie.MovieBooking.service;

import com.system.booking.movie.MovieBooking.entity.Screen;
import com.system.booking.movie.MovieBooking.entity.Seat;
import com.system.booking.movie.MovieBooking.entity.Theatre;
import com.system.booking.movie.MovieBooking.exception.ResourceNotFoundException;
import com.system.booking.movie.MovieBooking.repository.ScreenRepository;
import com.system.booking.movie.MovieBooking.repository.SeatRepository;
import com.system.booking.movie.MovieBooking.repository.TheatreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenService {
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private SeatRepository seatRepository;

    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    @Transactional
    public void addNewScreen(Screen screen) {
        Theatre theatre = theatreRepository.findById(screen.getTheatre().getTheatre_id())
                .orElseThrow(() -> new ResourceNotFoundException("Theatre not found"));
        screen.setTheatre(theatre);
        screenRepository.save(screen);
        for(int i = 0; i < screen.getTotal_seats();i++)
            seatRepository.save(new Seat("available",screen));
    }

    public void updateScreen(Screen screen) {
        Screen screenInDb = screenRepository.findById(screen.getScreen_id()).get();
        screenInDb.setTotal_seats(screen.getTotal_seats() != 0 ? screen.getTotal_seats() : screenInDb.getTotal_seats());
        screenRepository.save(screenInDb);
    }

    public void deleteScreen(int id) {
        screenRepository.deleteById(id);
    }
}
