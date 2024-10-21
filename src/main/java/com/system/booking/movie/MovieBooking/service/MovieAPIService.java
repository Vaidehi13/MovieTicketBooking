package com.system.booking.movie.MovieBooking.service;

import com.system.booking.movie.MovieBooking.response.MovieAPIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class MovieAPIService {

    // Replace this with your actual API key
    @Value("${movie.api.key}")
    private String apiKey;
    @Value("${movie.api.host}")
    private String apiHost;
    @Value("${movie.api.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    RedisService redisService;

    public List<MovieAPIResponse> callMovieAPI() {
        // Fetch data from Redis
        MovieAPIResponse movieAPIResponse = redisService.get("top_100_movies", MovieAPIResponse.class);
        if(movieAPIResponse != null){
            return List.of(movieAPIResponse);
        }else {
            // Setup the headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("x-rapidapi-key", apiKey); // Use the actual API key
            headers.set("x-rapidapi-host", apiHost); // Correct API host

            // Create an HttpEntity with the headers
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Make the API call
            ResponseEntity<MovieAPIResponse[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, MovieAPIResponse[].class);
            redisService.set("top_100_movies",response.getBody(),3000l);

            // Convert the array to a List and return
            return List.of(response.getBody());
        }
    }
}
