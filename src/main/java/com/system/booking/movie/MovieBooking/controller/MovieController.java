package com.system.booking.movie.MovieBooking.controller;


import com.system.booking.movie.MovieBooking.entity.Movie;
import com.system.booking.movie.MovieBooking.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/movie")
@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addNewMovie(@Valid @RequestBody Movie movie){
        movieService.addNewMovie(movie);
        return ResponseEntity.ok("Movie added successfully");
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie){
        movieService.updateMovie(movie);
        return ResponseEntity.ok("Movie updated successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable int id){
        movieService.deleteMovie(id);
        return ResponseEntity.ok("Movie deleted successfully");
    }
}
