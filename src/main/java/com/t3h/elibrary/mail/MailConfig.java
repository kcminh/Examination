package com.t3h.elibrary.mail;

import com.t3h.elibrary.common.ConfigProperties;
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
        mailSender.setHost(ConfigProperties.getData("host"));
        mailSender.setPort(Integer.parseInt(ConfigProperties.getData("port")));
        mailSender.setUsername(ConfigProperties.getData("username"));
        mailSender.setPassword(ConfigProperties.getData("password"));
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", ConfigProperties.getData("protocol"));
        props.put("mail.smtp.auth", ConfigProperties.getData("auth"));
        props.put("mail.smtp.starttls.enable", ConfigProperties.getData("starttls"));
        props.put("mail.debug", "true");
        props.put("mail.smtp.timeout", ConfigProperties.getData("timeout"));
        return mailSender;
    }
}
