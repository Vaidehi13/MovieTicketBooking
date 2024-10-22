package com.system.booking.movie.MovieBooking.controller;

import com.system.booking.movie.MovieBooking.entity.*;
import com.system.booking.movie.MovieBooking.exception.ResourceNotFoundException;
import com.system.booking.movie.MovieBooking.service.*;
import com.system.booking.movie.MovieBooking.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@Validated
@Slf4j
public class PublicController {
    @Autowired
    UserService userService;
    @Autowired
    TheatreService theatreService;
    @Autowired
    MovieService movieService;
    @Autowired
    ScreenService screenService;
    @Autowired
    private SeatService seatService;

    @Autowired
    private MovieAPIService movieAPIService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    // Register New User
    @PostMapping("/register")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
    //Login existing user
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        try{
            //Authenticate the credentials
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),user.getPassword()
            ));
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
            //Generate token for current user
            String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwtToken,HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
    // Fetch all theatres
    @GetMapping("/allTheatres")
    public ResponseEntity<?> getAllTheatres(){
        List<Theatre> theatreList = theatreService.getAllTheatres();
        if(theatreList != null && !theatreList.isEmpty()){
            return new ResponseEntity<>(theatreList, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("No theatres found");
    }
    //Fetch all movies
    @GetMapping("/allMovies")
    public ResponseEntity<?> getAllMovies(){
        List<Movie> movieList = movieService.getAllMovies();
        if(movieList != null && !movieList.isEmpty()){
            return new ResponseEntity<>(movieList, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("No movies found");
    }
    //Fetch all available screens
    @GetMapping("/allScreens")
    public ResponseEntity<?> getAllScreens(){
        List<Screen> screenList = screenService.getAllScreens();
        return new ResponseEntity<>(screenList,HttpStatus.OK);
    }
    //Fetch all screens by screen id
    @GetMapping("/allSeats/{screen_id}")
    public ResponseEntity<?> fetchAllSeatsofScreen(@PathVariable int screen_id){
        List<Seat> seatList = seatService.fetchAllSeatsOfScreen(screen_id);
        if(seatList != null && !seatList.isEmpty()) {
            return new ResponseEntity<>(seatList, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("No seats found for screen id "+screen_id);
    }
    @GetMapping("/api/movie")
    public ResponseEntity<?> getMovies(){
        return new ResponseEntity<>(movieAPIService.callMovieAPI(),HttpStatus.OK);
    }
}
