package com.example.back2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
@Transactional
@Controller
@RequestMapping("/User")
public class Enter {
    @RequestMapping("/Login")
    public String enter(){
        return "login";
    }
    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/personInfo")
    public String personInfo(){
        return "personInfo";
    }
    @RequestMapping("/personalAttendance")
    public String personalAttendance(){
        return "personalAttendance";
    }
    @RequestMapping("/companyPublic")
    public String companyPublic(){ return "companyPublic"; }
    @RequestMapping("/headPage")
    public String headPage(){ return "headPage"; }
}
