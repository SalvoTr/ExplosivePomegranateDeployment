package com.explosivepomegranate.rest.api.service;

import com.explosivepomegranate.rest.api.controller.BookController;
import com.explosivepomegranate.rest.api.model.Book;
import com.explosivepomegranate.rest.api.model.Borrowed;
import com.explosivepomegranate.rest.api.repository.AuthorRepository;
import com.explosivepomegranate.rest.api.repository.BookRepository;
import com.explosivepomegranate.rest.api.repository.BorrowedRepository;
import com.explosivepomegranate.rest.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    Borrowed reserveBook(Borrowed sendReservationInfo, Integer bookID) {
        Borrowed borrow = new Borrowed();
        borrow.setBook(bookRepository.getOne(bookID));
        borrow.setBookStatus(sendReservationInfo.isBookStatus());
        borrowedRepository.save(sendReservationInfo);
        return borrow;
    }

    /**
     * @author Sonja
     * UC8 my borrowed books
     */
    public List<Borrowed> getMyBorrows (Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        userDetails.getUserId();
        return borrowedRepository.findByUser_User_id(userDetails.getUserId());
    }

    /**
     * @author: Salvatore
     * returns list of all borrowed books (UC11)
     * */
    public List<Borrowed> getAllBorrowed() {
        return (List<Borrowed>) borrowedRepository.findAll();
       // return borrowedRepository.findAll();
    }


    /**
     * @author Sonja
     * UC18 add a comment to a book
     **/

    public @ResponseBody
    Borrowed addNewComment(String book_comment, Integer bookId) {
        Borrowed borrow = new Borrowed();
        borrow.setBook(bookRepository.getOne(bookId));
        borrow.setBookComment(book_comment);
        return borrow;
    }

}

