package com.explosivepomegranate.rest.api.service;

import com.explosivepomegranate.rest.api.config.SecurityConfig;
import com.explosivepomegranate.rest.api.model.Login;
import com.explosivepomegranate.rest.api.model.Role;
import com.explosivepomegranate.rest.api.model.User;
import com.explosivepomegranate.rest.api.repository.LoginRepository;
import com.explosivepomegranate.rest.api.repository.RoleRepository;
import com.explosivepomegranate.rest.api.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LoginRepository loginRepository;

    /**
     * @author: Salvatore
     * Creates a new user account (UC3)
     * */
    //public void saveNewUser(@Validated User user) throws Exception {
    public void saveNewUser(JsonNode jsonNode) throws Exception {

        User newUser = new User();
        newUser.setFirstname(jsonNode.get("firstname").asText());
        newUser.setLastname(jsonNode.get("lastname").asText());
        newUser.setEmail(jsonNode.get("email").asText());

        //Some basic checks to see if account already registered
       if (newUser.getId() == null) {
            if (userRepository.findByEmail(newUser.getEmail()) != null) {
                throw new Exception("Email address " + newUser.getEmail() + " already exists");
            }
        } else if (userRepository.findByEmailAndIdNot(newUser.getEmail(), newUser.getId()) != null) {
            throw new Exception("Email address " + newUser.getEmail() + " already exists");
        }

        Role userRole = roleRepository.findAll().get(1);
        newUser.setRole(userRole);
        newUser.setLogin(new Login());
        Login login = newUser.getLogin();
        login.setUser(newUser);
        login.setPassword(passwordEncoder.encode(jsonNode.get("password").asText()));
        userRepository.save(newUser);
    }

     /**
     * @author Clelia
     *  get the boolean if the logged-in user is an admin user
     *  */
    public Boolean isAdminRole(Authentication authentication) {
       CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
       String userRole = String.valueOf(userDetails.getAuthorities());
       if(userRole.equals("[ROLE_ADMIN]")) return true;
       return false;
    }
}
