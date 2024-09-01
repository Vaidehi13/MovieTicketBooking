package com.system.booking.movie.MovieBooking.controller;

import com.system.booking.movie.MovieBooking.entity.User;
import com.system.booking.movie.MovieBooking.service.CustomUserDetailsServiceImpl;
import com.system.booking.movie.MovieBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        //this endpoint is authenticated hence whatever is passed as username in Authentication Header
        // will be accessible, hence no need of @PathParam
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        userService.updateUser(username, user);
        return ResponseEntity.ok("User updated successfully");
    }
}
