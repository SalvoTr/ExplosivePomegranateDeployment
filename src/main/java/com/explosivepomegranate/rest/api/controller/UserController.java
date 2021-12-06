package com.explosivepomegranate.rest.api.controller;

import com.explosivepomegranate.rest.api.model.Borrowed;
import com.explosivepomegranate.rest.api.model.Login;
import com.explosivepomegranate.rest.api.model.Role;
import com.explosivepomegranate.rest.api.model.User;
import com.explosivepomegranate.rest.api.repository.LoginRepository;
import com.explosivepomegranate.rest.api.repository.RoleRepository;
import com.explosivepomegranate.rest.api.repository.UserRepository;
import com.explosivepomegranate.rest.api.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    //TEST PURPOSES:
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    RoleRepository roleRepository;

    /**
     * @author: Salvatore
     * Registers a new account (UC3)
     * */
    @PostMapping(path = "/myNewUser")
    public ResponseEntity<User> postNewUser(@RequestBody JsonNode jsonNode) {
        try{
            userService.saveNewUser(jsonNode);
        } catch (Exception e) {
           // TODO: now something is missing in the way it is saved. there is an error thrown, maybe cascade types? see link as reference
            // not sure if this helps but maybe a start https://www.baeldung.com/hibernate-unsaved-transient-instance-error
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
       // return ResponseEntity.accepted().body(user);
        // TODO: update this and update cascade types
        return null;
    }

    /**
     * @author Clelia
     * return USER_ROLE
     * */
    @GetMapping(path = "/userRole", produces = "application/json")
    public Boolean isAdminRole(Authentication authentication) {
        return userService.isAdminRole(authentication);
    }

    /**
    * @author: Salvatore
     * returns list of all users, roles, logins (for testing purposes)
     * */
    @GetMapping (path = "/allUserAccounts", produces = "application/json")
    public List<User> getUsers() { return userRepository.findAll(); }

    @GetMapping (path = "/allLogins", produces = "application/json")
    public List<Login> getLogins() { return loginRepository.findAll(); }

    @GetMapping (path = "/allRoles", produces = "application/json")
    public List<Role> getRoles() { return roleRepository.findAll(); }

}
