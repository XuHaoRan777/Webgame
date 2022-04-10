package com.webgame.controller;


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

}
