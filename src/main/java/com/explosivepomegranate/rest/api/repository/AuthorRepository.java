package com.explosivepomegranate.rest.api.repository;

import com.explosivepomegranate.rest.api.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//Salvatore - Interface for CURD functionality
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findAllByAuthorFirstnameAndAndAuthorLastname(String authorFirstname, String authorLastname);
}
