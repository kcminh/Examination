package com.t3h.elibrary.controller;

import com.t3h.elibrary.entity.UserInfo;
import com.t3h.elibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {
    @Autowired
    private UserService userInforService;

    @GetMapping("/student/list")
    public ModelAndView listAllStudent() {
        ModelAndView listStudentMV = new ModelAndView("user/student/list-student");
        listStudentMV.addObject("students", userInforService.getAllStudent());
        return listStudentMV;
    }

    @GetMapping("/student/{username}")
    public ModelAndView showDetailStudent(@PathVariable("username") String username,
                                          @ModelAttribute("students") UserInfo userInfo) throws Exception {
        ModelAndView showDetailMV = new ModelAndView("user/student/form");
        UserInfo userInfo1 = userInforService.getStudentByUsername(username);
        showDetailMV.addObject("students", userInfo1);
        return showDetailMV;
    }

    @PutMapping("/student/save")
    public ModelAndView saveStudent(@ModelAttribute("students") UserInfo userInfor) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/student/list");
        userInforService.saveUserInfor(userInfor);
        return mv;
    }

    @GetMapping("librarian/list")
    public ModelAndView listUser(@RequestParam(value = "name", required = false) String name) {
        ModelAndView modelAndView = new ModelAndView("ListUser");
        modelAndView.addObject("ModelUser", userInforService.listUser(name));
        return modelAndView;
    }
}
