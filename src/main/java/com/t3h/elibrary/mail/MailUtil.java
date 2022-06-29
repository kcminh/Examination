package com.t3h.elibrary.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class MailUtil {
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String subject, List<String> mailTo, List<String> mailCc,
                          List<String> mailBcc, String content, boolean isHtmlContent, List<String> pathFile)
            throws MessagingException {
//        System.out.println(emailSender);
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
        helper.setSubject(subject);
        helper.setTo(mailTo.toArray(new String[0]));
        if (mailCc != null)
            helper.setCc(mailCc.toArray(new String[0]));
        if (mailBcc != null)
            helper.setBcc(mailBcc.toArray(new String[0]));
        if (isHtmlContent)
            message.setContent(content, MediaType.TEXT_HTML_VALUE + ";charset=\"utf-8\"");
        else
            helper.setText(content);

        if (pathFile != null)
            for (String s : pathFile) {
                File file = new File(s);
                FileSystemResource fileAttach = new FileSystemResource(file);
                helper.addAttachment(file.getName(), fileAttach);
            }

        emailSender.send(message);
    }
}
