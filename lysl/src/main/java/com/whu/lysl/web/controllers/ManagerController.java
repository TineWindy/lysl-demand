package com.whu.lysl.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagerController {
    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    @RequestMapping("/left")
    public String left() {
        return "left";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/top")
    public String top() {
        return "top";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}