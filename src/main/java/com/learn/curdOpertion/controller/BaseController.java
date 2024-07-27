package com.learn.curdOpertion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @GetMapping("/")
        public String indexPage(){
        System.out.println("this page index function is called");
        return "index";
        }
    @GetMapping("/register")
    public String registerPage() {
        System.out.println("this page registe function is called");
        return "/view/register";
    }
    @GetMapping("/userDetailsPage")
    public String userDetails() {
        System.out.println("this page registe function is called");
        return "/view/userDetails";
    }

    @GetMapping("/login")
    public String loginPage() {
        System.out.println("this page login function is called");
        return "/view/login"; // Ensure you have a login.html template
    }
}
