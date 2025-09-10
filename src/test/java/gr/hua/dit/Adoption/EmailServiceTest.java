package gr.hua.dit.Adoption;

import gr.hua.dit.Adoption.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmailServiceTest {

    @MockBean
    private JavaMailSender javaMailSender; // mock αντικείμενο, δεν χρειάζεται πραγματικός mail server

    @Autowired
    private EmailService emailService; // η πραγματική service που θέλουμε να τεστάρουμε

    @Test
    void testSendEmail() {
        emailService.sendEmail("to@example.com", "Test Subject", "Test Body");

        verify(javaMailSender, atLeastOnce()).send(any(SimpleMailMessage.class));
    }
}
