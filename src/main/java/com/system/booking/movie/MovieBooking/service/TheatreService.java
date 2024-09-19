package com.system.booking.movie.MovieBooking.service;

import com.system.booking.movie.MovieBooking.entity.Theatre;
import com.system.booking.movie.MovieBooking.exception.ResourceNotFoundException;
import com.system.booking.movie.MovieBooking.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {
    @Autowired
    TheatreRepository theatreRepository;
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    public void addNewTheatre(Theatre theatre) {
        theatreRepository.save(theatre);
    }

    public void updateTheatre(Theatre theatre) {
        Theatre theatreInDb = theatreRepository.findById(theatre.getTheatre_id())
                .orElseThrow(() -> new ResourceNotFoundException("There is no such theatre"));
        if(theatreInDb != null) {
            theatreInDb.setTheatre_name(theatre.getTheatre_name() != null ? theatre.getTheatre_name() : theatreInDb.getTheatre_name());
            theatreInDb.setTheatre_address(theatre.getTheatre_address() != null ? theatre.getTheatre_address() : theatreInDb.getTheatre_address());
            theatreInDb.setTheatre_city(theatre.getTheatre_city() != null ? theatre.getTheatre_city() : theatreInDb.getTheatre_city());
            theatreInDb.setTheatre_state(theatre.getTheatre_state() != null ? theatre.getTheatre_state() : theatreInDb.getTheatre_state());
            theatreRepository.save(theatreInDb);
        }
    }

    public void deleteTheatre(int id) {
        theatreRepository.deleteById(id);
    }
}
