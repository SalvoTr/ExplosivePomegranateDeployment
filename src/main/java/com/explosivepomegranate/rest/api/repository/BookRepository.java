package com.explosivepomegranate.rest.api.repository;

import org.springframework.data.repository.CrudRepository;
import com.explosivepomegranate.rest.api.model.Book;

// This will be AUTO IMPLEMENTED by Spring into a Bean called bookRepository
// CRUD refers Create, Read, Update, Delete
public interface BookRepository extends CrudRepository<Book, Integer>{
}
