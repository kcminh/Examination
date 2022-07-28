package com.t3h.elibrary.controller;

import com.t3h.elibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/librarian")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/list")
    public ModelAndView listUser(@RequestParam(value = "name", required = false) String name) {
        ModelAndView modelAndView = new ModelAndView("/ListUser");
        modelAndView.addObject("ModelUser", userService.listUser(name));
        return modelAndView;
    }
}
