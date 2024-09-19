package com.system.booking.movie.MovieBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theatre_id;
    @NotBlank(message = "Theatre name cannot be blank")
    private String theatre_name;
    @NotBlank(message = "Theatre address cannot be blank")
    private String theatre_address;
    @NotBlank(message = "Theatre city cannot be blank")
    private String theatre_city;
    @NotBlank(message = "Theatre state cannot be blank")
    private String theatre_state;
}
