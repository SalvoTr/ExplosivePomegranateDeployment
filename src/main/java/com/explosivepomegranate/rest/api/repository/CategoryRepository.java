package com.explosivepomegranate.rest.api.repository;

import com.explosivepomegranate.rest.api.model.Book;
import com.explosivepomegranate.rest.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

//Salvatore - Interface for CURD functionality
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
