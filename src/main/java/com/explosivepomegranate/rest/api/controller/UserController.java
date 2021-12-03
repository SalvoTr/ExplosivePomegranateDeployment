package com.explosivepomegranate.rest.api.controller;

import com.explosivepomegranate.rest.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * @author Clelia
     * return USER_ROLE
     * */
    @GetMapping(path = "/userRole", produces = "application/json")
    public Boolean isAdminRole(Authentication authentication) {
        return userService.isAdminRole(authentication);
    }
}
