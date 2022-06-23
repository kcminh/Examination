package com.t3h.elibrary.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(ApplicationConfig.getData("host"));
        mailSender.setPort(Integer.parseInt(ApplicationConfig.getData("port")));
        mailSender.setUsername(ApplicationConfig.getData("username"));
        mailSender.setPassword(ApplicationConfig.getData("password"));
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", ApplicationConfig.getData("protocol"));
        props.put("mail.smtp.auth", ApplicationConfig.getData("auth"));
        props.put("mail.smtp.starttls.enable", ApplicationConfig.getData("starttls"));
        props.put("mail.debug", "true");
        return mailSender;
    }
}
