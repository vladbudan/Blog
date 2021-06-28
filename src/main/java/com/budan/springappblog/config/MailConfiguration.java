package com.budan.springappblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setPassword(env.getProperty("spring.mail.password"));
        Properties properties = mailSender.getJavaMailProperties();

        properties.setProperty("mail.transport.protocol", env.getProperty("mail.transport.protocol"));
        properties.setProperty("mail.debug", env.getProperty("mail.debug"));
        properties.setProperty("mail.smtp.auth", env.getProperty("spring.mail.properties.mail.smtp.auth"));
        properties.setProperty("mail.smtp.socketFactory.class", env.getProperty("mail.smtp.socketFactory.class"));
        properties.setProperty("mail.smtp.starttls.enable", env.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));

        return mailSender;
    }
}
