package com.system.booking.movie.MovieBooking.service;

import com.system.booking.movie.MovieBooking.entity.Screen;
import com.system.booking.movie.MovieBooking.entity.Theatre;
import com.system.booking.movie.MovieBooking.repository.ScreenRepository;
import com.system.booking.movie.MovieBooking.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenService {
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private TheatreRepository theatreRepository;

    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    public void addNewScreen(Screen screen) {
        Theatre theatre = theatreRepository.findById(screen.getTheatre().getTheatre_id())
                .orElseThrow(() -> new RuntimeException("Theatre not found"));
        screen.setTheatre(theatre);
        screenRepository.save(screen);
    }
}
