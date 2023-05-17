package com.test.postgresql_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/",""})
    public String index() {
        return "index";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/auth/join")
    public String join() {
        return "user/join";
    }


    @GetMapping("/user/detail")
    public String userDetail() {
        return "user/detail";
    }

    @GetMapping("/user/modify")
    public String userModify() {
        return "user/modify";
    }

    @GetMapping("/cfr")
    public String cfr() {
        return "cfr/cfr";
    }

}