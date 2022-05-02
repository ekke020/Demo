package com.backend.demo.repositories;

import com.backend.demo.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Set;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = "/create-data.sql")
@Sql(scripts = "/cleanup-data.sql", executionPhase = AFTER_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAllShouldReturnThree() {
        Set<User> users = userRepository.findAll();
        Assertions.assertEquals(3, users.size());
    }

    @Test
    public void findByIdShouldReturnOne() {
        User user = userRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getName(), "Adam Andersson");
        Assertions.assertEquals(user.getEmail(), "adam@mail.com");
    }

    @Test
    public void deleteByIdShouldRemoveOne() {
        userRepository.deleteById(1L);
        User user = userRepository.findById(1L).orElse(null);
        Assertions.assertNull(user);
    }

    @Test
    public void findByEmailReturnsUserOnValidEmail() {
        User user = userRepository.findByEmail("carl@mail.com").orElse(null);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getId(), 3L);
        user = userRepository.findByEmail("notReal@mail.com").orElse(null);
        Assertions.assertNull(user);
    }
}
