package com.webgame.controller;


import com.webgame.utils.HttpClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomePageController {

    @RequestMapping("/")
    public String mainPage(){
        return "index";
    }

    @GetMapping("/gamepage")
    public String gamepageAction(@RequestParam String gamepage){

        if("index".equals(gamepage)){
            return "gamePageIndex";
        }

        return "";
    }

    @ResponseBody
    @RequestMapping("/test")
    public String test(){
        String res = HttpClientUtil.doPost("http://116.233.72.161:7777//随便写写的实验.exe", null);

        System.out.println(res);
        return res;
    }

}
