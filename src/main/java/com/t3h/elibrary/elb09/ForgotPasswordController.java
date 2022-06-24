package com.t3h.elibrary.elb09;

import com.t3h.elibrary.common.ConfigProperties;
import com.t3h.elibrary.common.MessageProperties;
import com.t3h.elibrary.mail.MailUtil;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String doFogotPass(@ModelAttribute("userInfo") UserInfo userInfo, ModelMap modelMap) {
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
        if (repo.getByEmail(userInfo.getEmail()) == null) {
            modelMap.addAttribute("userInfo", userInfo);
            modelMap.addAttribute("error", MessageProperties.getData("msg003"));
            return "forgotPassword";
        }

        // gửi mail
        try {
            mailUtil.sendEmail(ConfigProperties.getData("subject"), Arrays.asList(userInfo.getEmail()),
                    null, null,String.format(ConfigProperties.getData("content"),userInfo.getEmail(),
                            ConfigProperties.getData("username")),
                    Boolean.parseBoolean(ConfigProperties.getData("isHtmlContent")), null);
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
