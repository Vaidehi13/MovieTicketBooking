# MovieTicketBooking
## Overview
This application is a comprehensive platform designed for booking movie tickets online.
It provides users with an intuitive interface to browse movies, select showtimes, and purchase tickets. 
The application aims to streamline the movie-going experience by offering a seamless and user-friendly booking process.

# Key Features

## User Authentication
User authentication is implemented using Spring Security with BCrypt for password encoding

## User Authentication using JWT
User authentication is implemented using JWT on the branch jwt.

## User Authorization
User authorization is implemented using Role Based Access

## Booked ticket expiry
An hourly updating scheduler is used to manage and update expired ticket bookings.

## Fetch top 100 famous movies
Integrated IMDB API to fetch top 100 famous movies with application using RESTTemplate

## Caching
Caching is implemented using Redis Cloud

## Application monitoring
The application health metrics are monitored with the help of actuator

# Technologies Used
+  Spring Boot
+ Spring Data JPA
+ Spring boot Security
+ RestTemplate
+ JWT
+ Redis
+ MySQL
+ JUnit 5
+ Mockito

