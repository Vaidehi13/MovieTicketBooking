package com.system.booking.movie.MovieBooking.service;

import com.system.booking.movie.MovieBooking.entity.User;
import com.system.booking.movie.MovieBooking.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void testFindByUsername(){
        assertNotNull(userRepository.findByUsername("V Sadawarte"));
    }
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user) {
        assertTrue(userService.addUser(user));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,10,12"
    }
    )
    public void test(int a, int b, int expected){
        assertEquals(expected,a + b);
    }
}
