package com.explosivepomegranate.rest.api.service;

import com.explosivepomegranate.rest.api.config.SecurityConfig;
import com.explosivepomegranate.rest.api.model.Login;
import com.explosivepomegranate.rest.api.model.Role;
import com.explosivepomegranate.rest.api.model.User;
import com.explosivepomegranate.rest.api.repository.LoginRepository;
import com.explosivepomegranate.rest.api.repository.RoleRepository;
import com.explosivepomegranate.rest.api.repository.UserRepository;
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
    public void saveNewUser(@Validated User user) throws Exception {
        //Some basic checks to see if account already registered
        if (user.getId() == null) {
            if (userRepository.findByEmail(user.getEmail()) != null) {
                throw new Exception("Email address " + user.getEmail() + " already exists");
            }
        } else if (userRepository.findByEmailAndIdNot(user.getEmail(), user.getId()) != null) {
            throw new Exception("Email address " + user.getEmail() + " already exists");
        }

        Role userRole = roleRepository.findAll().get(1);
        user.setRole(userRole);


        Login newLogin = user.getLogin();
        //Login newLogin = new Login();
        //newLogin.setUser(user);
        newLogin.setPassword(passwordEncoder.encode(newLogin.getPassword())); //TODO IDK how to get PW

        userRepository.save(user);
        loginRepository.save(newLogin);
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
