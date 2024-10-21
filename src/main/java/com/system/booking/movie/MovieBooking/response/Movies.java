package com.system.booking.movie.MovieBooking.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Movies {
    @JsonProperty("rank")
    public int rank;
    @JsonProperty("title")
    public String title;
    @JsonProperty("id")
    public String id;
}
