package com.system.booking.movie.MovieBooking.service;

import com.system.booking.movie.MovieBooking.entity.User;
import com.system.booking.movie.MovieBooking.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class CustomUserDetailsServiceImplTests {
    @InjectMocks
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void loadByUsernameTest(){
        when(userRepository.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(User.builder().username("V Sadawarte").password("123456789").roles(new ArrayList<>()).build());
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("V Sadawarte");
        Assertions.assertNotNull(userDetails);

    }
}
