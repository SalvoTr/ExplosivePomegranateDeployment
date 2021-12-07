package com.explosivepomegranate.rest.api.repository;

import com.explosivepomegranate.rest.api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}