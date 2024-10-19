package com.system.booking.movie.MovieBooking.service;

import com.system.booking.movie.MovieBooking.entity.User;
import com.system.booking.movie.MovieBooking.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public boolean addUser(User user) {
        if(user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password
//            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        }
        return false;
    }
    public void updateUser(String username,User user) {
        User userInDb = userRepository.findByUsername(username);
        if(userInDb != null){
            userInDb.setPassword(user.getPassword() != null ? passwordEncoder.encode(user.getPassword()) : customUserDetailsServiceImpl.loadUserByUsername(username).getPassword());
            userInDb.setEmail(user.getEmail() != null ? user.getEmail() : userInDb.getEmail());
            userInDb.setMobile_no(user.getMobile_no() != null ? user.getMobile_no() : userInDb.getMobile_no());
            userInDb.setAge(user.getAge() != 0? user.getAge() : userInDb.getAge());
            userRepository.save(userInDb);
        }
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
