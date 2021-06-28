package com.budan.springappblog.service.implementation;

import com.budan.springappblog.model.User;
import com.budan.springappblog.service.MailService;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.Environment;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@PropertySource("classpath:/mail.properties")
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final Environment env;

    MailServiceImpl(JavaMailSender mailSender, Environment env) {
        this.mailSender = mailSender;
        this.env = env;
    }

    public Boolean isNull(User user) {
        return StringUtils.isEmpty(user.getEmail());
    }

    public void send(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
}
