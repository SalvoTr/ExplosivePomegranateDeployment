package com.explosivepomegranate.rest.api.service;

import com.explosivepomegranate.rest.api.model.User;
import com.explosivepomegranate.rest.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
    }
}
