package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public ModelAndView index(ModelAndView modelAndView) {
        User user = new User(null, "admin", "123456", new Timestamp(System.currentTimeMillis()));
        userService.insert(user);
        List<User> users = userService.find();
        log.debug("{}", users);
        modelAndView.setViewName("index");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @RequestMapping("webjar")
    public ModelAndView webjar(ModelAndView modelAndView) {
        modelAndView.setViewName("webjar");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("json")
    public List<User> index() {
        return userService.find();
    }
}
