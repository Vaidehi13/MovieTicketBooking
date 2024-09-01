package com.system.booking.movie.MovieBooking.controller;

import com.system.booking.movie.MovieBooking.entity.Theatre;
import com.system.booking.movie.MovieBooking.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theatre")
public class TheatreController {
    @Autowired
    TheatreService theatreService;

    @PostMapping("/add")
    public ResponseEntity<String> addNewTheatre(@RequestBody Theatre theatre){
        theatreService.addNewTheatre(theatre);
        return ResponseEntity.ok("Theatre added successfully");
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateTheatre(@RequestBody Theatre theatre){
        theatreService.updateTheatre(theatre);
        return ResponseEntity.ok("Theatre updated successfully");
    }
}
