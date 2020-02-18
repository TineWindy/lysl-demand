package com.whu.lysl.web.controllers;

import com.whu.lysl.entity.vo.DemandVO;
import com.whu.lysl.service.demand.DemandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerController {

    @Resource
    private DemandService demandService;

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

    @RequestMapping("/right")
    public String right() {
        return "right";
    }

    @RequestMapping("/imgtable")
    public String imgtable() {
        return "imgtable";
    }

    @RequestMapping("/form")
    public String form() {
        return "form";
    }

    @RequestMapping("/imglist")
    public String imglist() {
        return "imglist";
    }

    @RequestMapping("/imglist1")
    public String imglist1() {
        return "imglist1";
    }

    @RequestMapping("/tools")
    public String tools() {
        return "tools";
    }

    @RequestMapping("/filelist")
    public String filelist() {
        return "filelist";
    }

    @RequestMapping("/tab")
    public String tab() {
        return "tab";
    }

    @RequestMapping("/defaultx")
    public String defaultx() {
        return "defaultx";
    }

    @RequestMapping("/computer")
    public String computer() {
        return "computer";
    }

    @RequestMapping("/jzxx")
    public String jzxx() {
        return "jzxx";
    }

    @RequestMapping("/xqwsh")
    public String xqwsh() {
        return "xqwsh";
    }

    @RequestMapping("/jzshz")
    public String jzshz(HttpServletRequest request) {
        String id = request.getParameter("id");
        System.out.println(id);
        request.setAttribute("admin", id);
        return "jzshz";
    }

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getList() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", 0);
        resultMap.put("msg", "");
        resultMap.put("count", "100");
        resultMap.put("sfd", "100");
        resultMap.put("afsd", "100");
        List<DemandVO> demandList = demandService.getUnreviewedDemands();
        resultMap.put("data", demandList);
        return resultMap;
    }
}