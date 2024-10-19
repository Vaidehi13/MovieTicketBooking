package com.system.booking.movie.MovieBooking.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.booking.movie.MovieBooking.entity.Theatre;
import com.system.booking.movie.MovieBooking.service.TheatreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TheatreControllerTest {

    @Mock
    private TheatreService theatreService;

    @InjectMocks
    private TheatreController theatreController;

    private MockMvc mockMvc;
    private Theatre theatre;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(theatreController).build();

        // Sample theatre entity
        theatre = new Theatre();
        theatre.setTheatre_id(1);
        theatre.setTheatre_name("AMC");
        theatre.setTheatre_address("123 Main St");
        theatre.setTheatre_city("New York");
        theatre.setTheatre_state("NY");
    }

    @Test
    void testAddNewTheatre() throws Exception {
        // Mock the service method
        doNothing().when(theatreService).addNewTheatre(any(Theatre.class));

        // Perform POST request to /theatre/add
        mockMvc.perform(post("/theatre/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(theatre)))  // Convert object to JSON
                .andExpect(status().isOk())
                .andExpect(content().string("Theatre added successfully"));

        // Verify that the service's addNewTheatre method was called once
        verify(theatreService, times(1)).addNewTheatre(any(Theatre.class));
    }

    @Test
    void testUpdateTheatre() throws Exception {
        // Mock the service method
        doNothing().when(theatreService).updateTheatre(any(Theatre.class));

        // Perform PUT request to /theatre/update
        mockMvc.perform(put("/theatre/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(theatre)))
                .andExpect(status().isOk())
                .andExpect(content().string("Theatre updated successfully"));

        // Verify that the service's updateTheatre method was called once
        verify(theatreService, times(1)).updateTheatre(any(Theatre.class));
    }

    @Test
    void testDeleteTheatre() throws Exception {
        // Mock the service method
        doNothing().when(theatreService).deleteTheatre(anyInt());

        // Perform DELETE request to /theatre/delete/1
        mockMvc.perform(delete("/theatre/delete/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("Theatre deleted successfully"));

        // Verify that the service's deleteTheatre method was called once
        verify(theatreService, times(1)).deleteTheatre(1);
    }
}