package com.explosivepomegranate.rest.api.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "login.html";
    }

    @GetMapping("/home")
    public String home() { return "book-search-admin.html"; }
}
