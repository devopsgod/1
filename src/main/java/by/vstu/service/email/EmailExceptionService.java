package by.vstu.service.email;

import by.vstu.utils.Exceptions;
import by.vstu.utils.Requests;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailExceptionService {

    private final EmailService emailService;

    private static final String EXCEPTION_MAIL_SUBJECT = "[Admission-committee][Exception]";

    @Value("${mailer.dev.exception}")
    private String emailTo;

    @Async
    public void sendExceptionEmail(Throwable throwable) {
        Map<String, Object> model = new HashMap<>();
        model.put("message", Exceptions.getRootMessage(throwable));
        model.put("class", Exceptions.getClass(throwable));
        model.put("stacktrace", Exceptions.getStackTrace(throwable));

        emailService.send(emailTo, EXCEPTION_MAIL_SUBJECT, "email/exception", model);
    }

    @Async
    public void sendInvalidFormatExceptionEmail(Throwable throwable, HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("message", Exceptions.getRootMessage(throwable));
        model.put("class", Exceptions.getClass(throwable));
        model.put("stacktrace", Exceptions.getStackTrace(throwable));
        model.put("remoteAddr", Requests.getRemoteAddr(request));
        model.put("requestMethod", Requests.getRequestMethod(request));
        model.put("requestUri", Requests.getRequestUri(request));

        emailService.send(emailTo, EXCEPTION_MAIL_SUBJECT, "email/exception_invalid_format", model);
    }
}
