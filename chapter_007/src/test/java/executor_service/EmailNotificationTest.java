package executor_service;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EmailNotificationTest {

    @Test
    public void whenSendNotificationThenSent() {
        List<User> users = List.of(
                new User("dex", "d_dexter@mail.ru"),
                new User("men", "men@mail.ru")
        );

        List<String> expectedEmails = new ArrayList<>();

        EmailNotification emailNotification = new EmailNotification() {
            @Override
            void send(String subject, String body, String email) {
                expectedEmails.add(email);
            }
        };

        for (User user : users) {
            emailNotification.emailTo(user);
        }

        emailNotification.close();

        assertThat(expectedEmails, is(List.of("d_dexter@mail.ru", "men@mail.ru")));
    }

}