package com.explosivepomegranate.rest.api.repository;

import com.explosivepomegranate.rest.api.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Integer> {
}