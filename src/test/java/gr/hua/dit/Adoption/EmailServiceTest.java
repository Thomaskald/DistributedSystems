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
class EmailServiceTest {

    @MockBean
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail() {
        emailService.sendEmail("test@example.com", "Subject", "Body");
        verify(javaMailSender, atLeastOnce()).send(any(SimpleMailMessage.class));
    }
}
