package com.system.booking.movie.MovieBooking.controller;

import com.system.booking.movie.MovieBooking.entity.Screen;
import com.system.booking.movie.MovieBooking.entity.Theatre;
import com.system.booking.movie.MovieBooking.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/update")
    public ResponseEntity<String> updateScreen(@RequestBody Screen screen) {
        screenService.updateScreen(screen);
        return ResponseEntity.ok("Screen updated successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteScreen(@PathVariable int id) {
        screenService.deleteScreen(id);
        return ResponseEntity.ok("Screen deleted successfully");
    }
}
