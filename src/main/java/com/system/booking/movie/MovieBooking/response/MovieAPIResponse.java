package com.system.booking.movie.MovieBooking.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root[] root = om.readValue(myJsonString, Root[].class); */

@Getter
@Setter
public class MovieAPIResponse{
    @JsonProperty("rank")
    public int rank;
    @JsonProperty("title")
    public String title;
    @JsonProperty("id")
    public String id;
}


