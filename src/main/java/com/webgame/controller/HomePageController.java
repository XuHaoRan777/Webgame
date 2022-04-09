package com.webgame.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomePageController {

    @RequestMapping("/")
    public String mainPage(){
        return "index";
    }

    @ResponseBody
    @PostMapping("/test")
    public String test(){
        return "请求成功！";
    }

}
