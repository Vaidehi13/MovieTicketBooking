package com.system.booking.movie.MovieBooking.service;

import com.system.booking.movie.MovieBooking.dto.MovieDto;
import com.system.booking.movie.MovieBooking.entity.Movie;
import com.system.booking.movie.MovieBooking.entity.Screen;
import com.system.booking.movie.MovieBooking.entity.Theatre;
import com.system.booking.movie.MovieBooking.exception.ResourceNotFoundException;
import com.system.booking.movie.MovieBooking.repository.MovieRepository;
import com.system.booking.movie.MovieBooking.repository.ScreenRepository;
import com.system.booking.movie.MovieBooking.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    ScreenRepository screenRepository;
    public Set<Integer> getScreenIds(Movie movie){
        return movie.getScreens().stream().map(Screen::getScreen_id).collect(Collectors.toUnmodifiableSet());
    }
    public List<MovieDto> getAllMovies() {
        List<Movie> movies =  movieRepository.findAll();
        return movies.stream().map(
                movie -> MovieDto.builder()
                        .movie_id(movie.getMovie_id())
                        .movie_name(movie.getMovie_name())
                        .start_time(movie.getStart_time())
                        .screen_id(getScreenIds(movie))
                        .build()
        ).collect(Collectors.toUnmodifiableList());
    }

    public void addNewMovie(Movie movie) {
        Set<Screen> screens = movie.getScreens();
        Set<Screen> screenSet = new HashSet<>();
        for(Screen s : screens){
            Screen screen = screenRepository.findById(s.getScreen_id())
                    .orElseThrow(()-> new ResourceNotFoundException("There is no screen with this id"));
            screenSet.add(screen);
        }
        movie.setScreens(screenSet);
        movieRepository.save(movie);
    }

    public void updateMovie(Movie movie) {
        Movie movieInDb = movieRepository.findById(movie.getMovie_id()).orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        if (movieInDb != null) {
            movieInDb.setMovie_name(movie.getMovie_name() != null ? movie.getMovie_name() : movieInDb.getMovie_name());
            movieInDb.setStart_time(movie.getStart_time() != null ? movie.getStart_time() : movieInDb.getStart_time());

            Set<Screen> updateScreens = movie.getScreens();
            Set<Screen> screensInDb = movieInDb.getScreens();

            for (Screen s : updateScreens) {
                boolean existsInDb = false;

                for (Screen sn : screensInDb) {
                    if (sn.getScreen_id() == s.getScreen_id()) {
                        existsInDb = true;
                        Screen screenInDb = screenRepository.findById(sn.getScreen_id()).orElseThrow(() -> new ResourceNotFoundException("Screen not found"));
                        if (screenInDb != null) {
                            Theatre theatre = theatreRepository.findById(screenInDb.getTheatre().getTheatre_id()).orElseThrow(() -> new ResourceNotFoundException("Theatre not found"));
                            if (theatre != null) {
                                screenInDb.setTheatre(theatre);
                                screenInDb.setTotal_seats(s.getTotal_seats());
                            }
                        }
                        break;  // Screen found, no need to check further
                    }
                }

                if (!existsInDb) {
                    // Add the new screen to the set in the movie
                    Theatre theatre = theatreRepository.findById(s.getTheatre().getTheatre_id()).orElse(null);
                    if (theatre != null) {
                        s.setTheatre(theatre);
                        screensInDb.add(s);
                    }
                }
            }

            movieInDb.setScreens(screensInDb);
            movieRepository.save(movieInDb);
        }
    }

    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }
}
