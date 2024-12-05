import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

public class EmailServiceTest {

    @Test
    public void testSendEmail() {
        JavaMailSender mailSender = Mockito.mock(JavaMailSender.class);
        EmailService emailService = new EmailService(mailSender);

        emailService.sendEmail("test@example.com", "Test Subject", "Test Message");

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
