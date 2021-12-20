package com.explosivepomegranate.rest.api.repository;

import com.explosivepomegranate.rest.api.model.Book;
import com.explosivepomegranate.rest.api.model.Borrowed;
import com.explosivepomegranate.rest.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BorrowedRepository extends JpaRepository<Borrowed, Integer> {

    /**
     * @author Sonja
     * Methode for returing user id
     */
    List<Borrowed> findByUser(User user);

    List<Borrowed> findByUserAndBook(User user, Book book);

    List<Borrowed> findByBook(Book book);

    List<Borrowed> findByBook_CurrentlyBorrowedOrderByIdDesc(boolean currentlyBorrowed);

}
