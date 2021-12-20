package com.explosivepomegranate.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.explosivepomegranate.rest.api.model.Book;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called bookRepository
// CRUD refers Create, Read, Update, Delete
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findDistinctByCategories_Id(int id);

    List<Book> findDistinctByAuthors_Id(int id);

    List<Book> findByCategories_CategoryName(String categoryName);

}
