package com.explosivepomegranate.rest.api.service;

import com.explosivepomegranate.rest.api.model.User;
import com.explosivepomegranate.rest.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
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
     * @author: Salvatore
     * Creates a new user account (UC3)
     * */
    public void saveNewUser(@Validated User user) throws Exception{
        if (user.getId() == null) {
            if (userRepository.findByEmail(user.getEmail()) != null) {
                throw new Exception("Email address " + user.getEmail() + " already exists");
            }
        } else if (userRepository.findByEmailAndIdNot(user.getEmail(), user.getId()) != null) {
            throw new Exception("Email address " + user.getEmail() + " already exists");
        }
        //user.setPassword(passwordEncoder.encode(user.getPassword())); TODO ask security-expert Meneghin
        userRepository.save(user);

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
