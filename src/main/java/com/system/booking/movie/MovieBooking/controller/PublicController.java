package com.system.booking.movie.MovieBooking.controller;

import com.system.booking.movie.MovieBooking.entity.Movie;
import com.system.booking.movie.MovieBooking.entity.Screen;
import com.system.booking.movie.MovieBooking.entity.Theatre;
import com.system.booking.movie.MovieBooking.entity.User;
import com.system.booking.movie.MovieBooking.service.MovieService;
import com.system.booking.movie.MovieBooking.service.ScreenService;
import com.system.booking.movie.MovieBooking.service.TheatreService;
import com.system.booking.movie.MovieBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserService userService;
    @Autowired
    TheatreService theatreService;
    @Autowired
    MovieService movieService;
    @Autowired
    ScreenService screenService;

    // Register New User
    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
    // Fetch all theatres
    @GetMapping("/allTheatres")
    public ResponseEntity<?> getAllTheatres(){
        List<Theatre> theatreList = theatreService.getAllTheatres();
        if(theatreList != null && !theatreList.isEmpty()){
            return new ResponseEntity<>(theatreList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //Fetch all movies
    @GetMapping("/allMovies")
    public ResponseEntity<?> getAllMovies(){
        List<Movie> movieList = movieService.getAllMovies();
        if(movieList != null && !movieList.isEmpty()){
            return new ResponseEntity<>(movieList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //Fetch all available screens
    @GetMapping("/allScreens")
    public ResponseEntity<?> getAllScreens(){
        List<Screen> screenList = screenService.getAllScreens();
        return new ResponseEntity<>(screenList,HttpStatus.OK);
    }
}
