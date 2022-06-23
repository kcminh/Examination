package com.t3h.elibrary.controller;

import com.t3h.elibrary.mail.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    private MailUtil mailUtil;

    @GetMapping("/send-mail")
    public String testSendMail() throws MessagingException {
        System.out.println("mailUtil:" + mailUtil);
        List<String> mailTo = new ArrayList<>();
        mailTo.add("phuongtran95st@gmail.com");
        String subject = "Test mail";
        String content = "<h3>Im testing send a HTML email</h3>"
                + "<img src='http://www.apache.org/images/asf_logo_wide.gif'>";

        mailUtil.sendEmail(subject, mailTo, null, null, content, true, null);

        return "Success.";
    }
}
