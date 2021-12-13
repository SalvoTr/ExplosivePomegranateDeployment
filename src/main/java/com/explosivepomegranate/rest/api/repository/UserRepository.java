package com.explosivepomegranate.rest.api.repository;

import com.explosivepomegranate.rest.api.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
