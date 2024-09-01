package com.system.booking.movie.MovieBooking.repository;

import com.system.booking.movie.MovieBooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
