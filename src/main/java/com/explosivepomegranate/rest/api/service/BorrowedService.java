package com.explosivepomegranate.rest.api.service;

import com.explosivepomegranate.rest.api.controller.BookController;
import com.explosivepomegranate.rest.api.model.Book;
import com.explosivepomegranate.rest.api.model.Borrowed;
import com.explosivepomegranate.rest.api.model.User;
import com.explosivepomegranate.rest.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;
import java.util.List;

@Service
public class BorrowedService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowedRepository borrowedRepository;
    @Autowired
    private UserRepository userRepository;


    /**
     * @author Sonja
     * UC7 borrow a book, set start date is today + 14 days for end date, SQL date format
     */

    public Borrowed reserveBook(Integer bookID, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        User user = userRepository.findById(userDetails.getUserId());
        Borrowed borrow = new Borrowed();
        Book book = bookRepository.findById(bookID).get();
        Calendar calendar = Calendar.getInstance();

        // todays date
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        Date today = new Date(calendar.getTimeInMillis());
        // date in two weeks
        calendar.add(Calendar.DATE, 14);
        Date twoWeeks = new Date(calendar.getTimeInMillis());

        borrow.setBook(book);
        borrow.setBookStatus(true);
        borrow.setUser(user);
        borrow.setStartDate(today);
        borrow.setInitEndDate(twoWeeks);
        borrowedRepository.save(borrow);
        // to save the new book status we need to save the changes in book too
        bookRepository.save(book);
        return borrow;
    }

    /**
     * @author Sonja
     * UC8 my borrowed books
     */
    public List<Borrowed> getMyBorrows (Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        userDetails.getUserId();
        User user = userRepository.findById(userDetails.getUserId());
        return borrowedRepository.findByUser(user);
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

