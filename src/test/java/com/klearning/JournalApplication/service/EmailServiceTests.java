package com.klearning.JournalApplication.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail() {
        emailService.sendEmail("ketan1waghmare@gmail.com",
                "Testing java mail sender",
                "Hi , Kaisa chal raha hai sab ?");
    }
}
