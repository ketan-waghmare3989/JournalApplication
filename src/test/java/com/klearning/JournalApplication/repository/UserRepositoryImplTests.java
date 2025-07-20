package com.klearning.JournalApplication.repository;

import com.klearning.JournalApplication.service.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTests {


    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void testSentimantUsers() {
        Assertions.assertNotNull(userRepository.getUserSA());
    }
}
