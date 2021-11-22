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
    public String home() { return "Admin/book-search-admin.html"; }

    @GetMapping("/register")
    public String register() { return "register.html"; }

    @GetMapping("/myProfile")
    public String profile() { return "Admin/profile-admin.html"; }

    @GetMapping("/currentlyBorrowed")
    public String borrowed() { return "Admin/borrowed-books-overview.html"; }
}
