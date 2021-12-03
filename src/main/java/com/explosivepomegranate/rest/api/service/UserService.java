package com.explosivepomegranate.rest.api.service;

import com.explosivepomegranate.rest.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * @author Clelia
     *  get the boolean if the logged in user is an admin suer
     *  */
    public Boolean isAdminRole(Authentication authentication) {
       CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
       String userRole = String.valueOf(userDetails.getAuthorities());
       if(userRole.equals("[ROLE_ADMIN]")) return true;
       return false;
    }
}
