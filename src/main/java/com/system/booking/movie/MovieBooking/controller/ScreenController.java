package com.system.booking.movie.MovieBooking.controller;

import com.system.booking.movie.MovieBooking.entity.Screen;
import com.system.booking.movie.MovieBooking.entity.Theatre;
import com.system.booking.movie.MovieBooking.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/screen")
public class ScreenController {
    @Autowired
    ScreenService screenService;
    @PostMapping("/add")
    public ResponseEntity<String> addNewScreen(@RequestBody Screen screen) {
        screenService.addNewScreen(screen);
        return ResponseEntity.ok("Screen added successfully");
    }
}
