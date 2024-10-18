package com.system.booking.movie.MovieBooking.controller;

import com.system.booking.movie.MovieBooking.entity.User;
import com.system.booking.movie.MovieBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/allUsers")
    public ResponseEntity<?> fetchAllUsers(){
        List<User> users = userService.getAllUsers();
        if(users != null && !users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
