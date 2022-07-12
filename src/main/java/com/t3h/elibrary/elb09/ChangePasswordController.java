package com.t3h.elibrary.elb09;

import com.t3h.elibrary.common.MessageProperties;
import com.t3h.elibrary.common.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ChangePasswordController {

    @Autowired
    UserReporsitory userRepo;

    @GetMapping("/changePassword")
    public String changePassword(ModelMap modelMap, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        if (userId == null) {
            modelMap.addAttribute("callForm", MessageProperties.getData("msg005"));
            return "changePassword";
        }
        PasswordReset passReset = new PasswordReset();
        passReset.setUserId((Integer) userId);
        modelMap.addAttribute("passReset", passReset);
        return "changePassword";
    }

    @PostMapping("/do-change-password")
    public String doChangePassword(@ModelAttribute("passReset") PasswordReset passwordReset, ModelMap modelMap) {
        System.out.println(passwordReset);
        if (passwordReset.getNewPassword() == null || passwordReset.getNewPassword().isEmpty()) {
            modelMap.addAttribute("error", MessageProperties.getData("msg006"));
            return "changePassword";
        }
        if (passwordReset.getConfirmPassword() == null || passwordReset.getConfirmPassword().isEmpty()) {
            modelMap.addAttribute("error", MessageProperties.getData("msg007"));
            return "changePassword";
        }
        if (!PasswordValidator.isValid(passwordReset.getConfirmPassword())) {
            modelMap.addAttribute("error", MessageProperties.getData("msg009"));
            modelMap.addAttribute("passReset",passwordReset);
            return "changePassword";
        }
        if (!passwordReset.getConfirmPassword().equals(passwordReset.getNewPassword())) {
            modelMap.addAttribute("error", MessageProperties.getData("msg008"));
            modelMap.addAttribute("passReset",passwordReset);
            return "changePassword";
        }
        String newPassEncoded = new BCryptPasswordEncoder().encode(passwordReset.getConfirmPassword());
        UserInfo newUserInfo = userRepo.getByUserId(passwordReset.getUserId());
        newUserInfo.setPassword(newPassEncoded);
        userRepo.save(newUserInfo);
        modelMap.addAttribute("passReset",passwordReset);
        modelMap.addAttribute("success",MessageProperties.getData("msg010"));
        return "changePassword";
    }
}
