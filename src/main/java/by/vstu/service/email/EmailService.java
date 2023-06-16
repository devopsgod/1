package by.vstu.service.email;

import by.vstu.exception.BusinessException;
import by.vstu.service.thymeleaf.ThymeleafTemplateService;
import by.vstu.utils.Settings;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final Locale EMAIL_LOCALE = Locale.ENGLISH;

    private final JavaMailSender mailSender;

    private final ThymeleafTemplateService thymeleafTemplateService;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public void send(String mailTo, String subject, String template, Map<String, Object> model) {
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            msg.setFrom(new InternetAddress(emailFrom, Settings.EMAIL_SENDER_NAME));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
            msg.setSubject(subject);
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, StandardCharsets.UTF_8.name());
            String text = thymeleafTemplateService.process(template, EMAIL_LOCALE, model);
            helper.setText(text, true);
            mailSender.send(msg);
        } catch (MessagingException ex) {
            throw new BusinessException("Email can't be send. Smth wrong!");
        } catch (UnsupportedEncodingException ex) {
            throw new BusinessException("Email can't be send. Encoding issue!");
        }
    }
}
