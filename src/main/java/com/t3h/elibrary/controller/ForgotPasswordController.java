package com.t3h.elibrary.controller;

import com.t3h.elibrary.common.ConfigProperties;
import com.t3h.elibrary.common.MessageProperties;
import com.t3h.elibrary.entity.UserInfo;
import com.t3h.elibrary.repository.UserReporsitory;
import com.t3h.elibrary.mail.MailUtil;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class ForgotPasswordController {

    @Autowired
    private UserReporsitory repo;
    @Autowired
    private MailUtil mailUtil;

    @GetMapping("/forgot-password")
    public String forgotPass(Model model) {
        model.addAttribute("userInfo", new UserInfo());
        return "forgotPassword";
    }

    @PostMapping("/do-forgot-password")
    public String doFogotPass(@ModelAttribute("userInfo") UserInfo userInfo, ModelMap modelMap, HttpServletRequest request) {
        if (userInfo == null) {
            modelMap.addAttribute("userInfo", new UserInfo());
            modelMap.addAttribute("error", MessageProperties.getData("msg001"));
            return "forgotPassword";
        }
        if (!EmailValidator.getInstance().isValid(userInfo.getEmail())) {
            modelMap.addAttribute("userInfo", userInfo);
            modelMap.addAttribute("error", MessageProperties.getData("msg002"));
            return "forgotPassword";
        }

        UserInfo user = repo.getByEmail(userInfo.getEmail());

        if (user == null) {
            modelMap.addAttribute("userInfo", userInfo);
            modelMap.addAttribute("error", MessageProperties.getData("msg003"));
            return "forgotPassword";
        }

        // gửi mail
        try {
            mailUtil.sendEmail(ConfigProperties.getData("subject"), Arrays.asList(userInfo.getEmail()),
                    null, null, String.format(ConfigProperties.getData("content"), userInfo.getEmail(),
                            ConfigProperties.getData("username")),
                    Boolean.parseBoolean(ConfigProperties.getData("isHtmlContent")), null);
            // Luu session for user info voi thoi gian 30p
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getUserId());
            session.setMaxInactiveInterval(1800);

        } catch (Exception e) {
            modelMap.addAttribute("userInfo", userInfo);
            modelMap.addAttribute("error", e.getMessage());
            return "forgotPassword";
        }
        modelMap.addAttribute("userInfo", userInfo);
        modelMap.addAttribute("success", MessageProperties.getData("msg004"));
        return "forgotPassword";
    }
}
