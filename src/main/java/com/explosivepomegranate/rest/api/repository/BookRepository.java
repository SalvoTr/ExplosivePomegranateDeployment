package com.explosivepomegranate.rest.api.repository;

import com.explosivepomegranate.rest.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.explosivepomegranate.rest.api.model.Book;

import java.util.List;
import java.util.Set;

// This will be AUTO IMPLEMENTED by Spring into a Bean called bookRepository
// CRUD refers Create, Read, Update, Delete
public interface BookRepository extends JpaRepository<Book, Integer> {

    //List<Book> findByCategories_Id(int id);
    List<Book> findDistinctByCategories_Id(int id);

    //List<Book> findByAuthors_Id(int id);
    List<Book> findDistinctByAuthors_Id(int id);

    List<Book> findByCategories_CategoryName(String categoryName);




}
