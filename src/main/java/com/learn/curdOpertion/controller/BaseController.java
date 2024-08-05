package com.learn.curdOpertion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @GetMapping("/")
        public String indexPage(){
        System.out.println("this page index function is called");
        return "index.html";
        }


    @GetMapping("/Home")
    public String HomePage(){
        System.out.println("this page index function is called");
        return "index.html";
    }
    @GetMapping("/Register User")
    public String registerPage() {
        System.out.println("this page registe function is called");
        return "/view/register.html";
    }
    @GetMapping("/userDetailsPage")
    public String userDetails() {
        System.out.println("this page registe function is called");
        return "/view/userDetails.html";
    }
    @GetMapping("/navbars")
    public String navbars() {
        System.out.println("nav bar redirect function is called");
        return "navbar.html";
    }
    @GetMapping("/manuCreate")
    public String menuCreate() {
        System.out.println("this page manuCreate function is called");
        return "/view/manuCreate.html";
    }

    @GetMapping("/login")
    public String loginPage() {
        System.out.println("this page login function is called");
        return "/view/login.html"; // Ensure you have a login.html template
    }
}
