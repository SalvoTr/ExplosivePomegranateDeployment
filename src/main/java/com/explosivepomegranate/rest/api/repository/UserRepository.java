package com.explosivepomegranate.rest.api.repository;

import com.explosivepomegranate.rest.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    User findByEmailAndIdNot(String email, int id);

    User findById(int id);
}
