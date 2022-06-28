package com.t3h.elibrary.controller;

import com.t3h.elibrary.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("librarian")
//Anotation in
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("list")
    public ModelAndView listUser(
            @RequestParam(value = "name", required = false) String name) {
        log.info(name);
        ModelAndView modelAndView = new ModelAndView("ListUser");
        modelAndView.addObject("ModelUser", userService.listUser(name));
        return modelAndView;
    }
}
