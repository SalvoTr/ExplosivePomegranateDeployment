package com.explosivepomegranate.rest.api.controller;

import com.explosivepomegranate.rest.api.model.User;
import com.explosivepomegranate.rest.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController //User Endpoint
public class UserController {

    @Autowired
    UserService userService;

    /**
     * @author: Salvatore
     * Creates an account (UC3)
     * */
    @PostMapping(path = "/myNewUser") //TODO register instead of myNewUser?
    public ResponseEntity<Void> postNewUser(@RequestBody User user){
        try{
            userService.saveNewUser(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.ok().build();
    }


}
