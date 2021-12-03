package com.explosivepomegranate.rest.api.service;

import com.explosivepomegranate.rest.api.model.Book;
import com.explosivepomegranate.rest.api.model.Borrowed;
import com.explosivepomegranate.rest.api.repository.AuthorRepository;
import com.explosivepomegranate.rest.api.repository.BookRepository;
import com.explosivepomegranate.rest.api.repository.BorrowedRepository;
import com.explosivepomegranate.rest.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class BorrowedService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BorrowedRepository borrowedRepository;



    /**
     * @author Sonja
     * UC7 borrow a book, set start date is today + 14 days for end date, SQL date format
     */

    public @ResponseBody
    Borrowed reserveBook (Borrowed sendReservationInfo) {
        Borrowed borrow = new Borrowed();
        borrow.setBookComment(sendReservationInfo.getBookComment());
        borrow.setBorrowed_id(sendReservationInfo.getBorrow_id());
        borrow.setBookStatus(sendReservationInfo.isBookStatus());
        borrowedRepository.save(sendReservationInfo);
    return borrow;
    }
}
