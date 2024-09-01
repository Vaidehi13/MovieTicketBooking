package com.system.booking.movie.MovieBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theatre_id;
    private String theatre_name;
    private String theatre_address;
    private String theatre_city;
    private String theatre_state;
}
