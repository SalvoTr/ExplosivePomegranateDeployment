package com.explosivepomegranate.rest.api.repository;

import com.explosivepomegranate.rest.api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowedRepository extends JpaRepository<Book, Integer> {
}
