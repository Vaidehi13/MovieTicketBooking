package com.system.booking.movie.MovieBooking.service;

import com.system.booking.movie.MovieBooking.dto.TheatreDto;
import com.system.booking.movie.MovieBooking.entity.Theatre;
import com.system.booking.movie.MovieBooking.exception.ResourceNotFoundException;
import com.system.booking.movie.MovieBooking.repository.TheatreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TheatreServiceTest {
    @Mock
    private TheatreRepository theatreRepository;

    @InjectMocks
    private TheatreService theatreService;

    private Theatre theatre;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        theatre = new Theatre();
        theatre.setTheatre_id(1);
        theatre.setTheatre_address("Hinjewadi Phase 2");
        theatre.setTheatre_city("Pune");
        theatre.setTheatre_name("Grand High Street");
        theatre.setTheatre_state("Maharashtra");
    }

    @Test
    void getAllTheatresTest(){
        List<Theatre> theatres = Arrays.asList(theatre);
        when(theatreRepository.findAll()).thenReturn(theatres);

        List<TheatreDto> result = theatreService.getAllTheatres();
        assertEquals(1, result.size());
        assertEquals("Grand High Street", result.get(0).getTheatre_name());
        verify(theatreRepository, times(1)).findAll();
    }

    @Test
    void addNewTheatreTest(){
        when(theatreRepository.save(any(Theatre.class))).thenReturn(theatre);
        theatreService.addNewTheatre(theatre);
        verify(theatreRepository,times(1)).save(theatre);
    }

    @Test
    void testUpdateTheatre() {
        when(theatreRepository.findById(theatre.getTheatre_id())).thenReturn(Optional.of(theatre));

        Theatre updatedTheatre = new Theatre();
        updatedTheatre.setTheatre_id(1);
        updatedTheatre.setTheatre_name("Updated AMC");
        updatedTheatre.setTheatre_city("Los Angeles");

        theatreService.updateTheatre(updatedTheatre);

        verify(theatreRepository, times(1)).save(theatre);
        assertEquals("Updated AMC", theatre.getTheatre_name());
        assertEquals("Los Angeles", theatre.getTheatre_city());
    }

    @Test
    void testUpdateTheatreNotFound() {
        when(theatreRepository.findById(1)).thenReturn(Optional.empty());

        Theatre updatedTheatre = new Theatre();
        updatedTheatre.setTheatre_id(1);
        updatedTheatre.setTheatre_name("Updated AMC");

        assertThrows(ResourceNotFoundException.class, () -> theatreService.updateTheatre(updatedTheatre));

        verify(theatreRepository, times(0)).save(any(Theatre.class));
    }

    @Test
    void testDeleteTheatre() {
        doNothing().when(theatreRepository).deleteById(1);

        theatreService.deleteTheatre(1);

        verify(theatreRepository, times(1)).deleteById(1);
    }
}
