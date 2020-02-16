package com.whu.lysl.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagerController {
    @RequestMapping("/index")
    public String index() {
        return "/web/hospital/hospitalIndex";
    }
}