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

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
        return borrowedRepository.findByUser(findAuthenticatedUser(authentication));
    }

    /**
     * @author: Salvatore
     * returns list of all borrowed books (UC11)
     * */
    public List<Borrowed> getAllBorrowed() {
        List<Borrowed> allBorrowed = borrowedRepository.findByBook_CurrentlyBorrowedOrderByIdDesc(true);

        // https://newbedev.com/remove-duplicates-from-a-list-of-objects-based-on-property-in-java-8
        // Had to adjust the code: as you need to have comparingInt for the new TreeSet, I couldn't simply compare
        // the Borrowed_ID to filter duplicate Books because each Borrowed object has a unique ID but still might have the same Book
        // work around: created getBookIdFromBorrowed() in Borrowed model
        List<Borrowed> filteredAllBorrowed = allBorrowed.stream()
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(Borrowed::getBookIdFromBorrowed))),
                        ArrayList::new));

       return filteredAllBorrowed;
    }


    /**
     * @author Sonja
     * UC18 add a comment to a book
     **/
    public Borrowed addNewComment(String book_comment, Integer bookId, Authentication authentication) {
        Book book = bookRepository.findById(bookId).get();
        List<Borrowed> allBorrowed = borrowedRepository.findByUserAndBook(findAuthenticatedUser(authentication),book);
        // get the last entry ( newest one added)
        Borrowed borrowed = allBorrowed.get(allBorrowed.size()-1);
        // if there is already a comment from the same user on the same borrowing then just combine the content
        if(borrowed.getBookComment()== null) {
            borrowed.setBookComment(book_comment);
        } else {
            borrowed.setBookComment(borrowed.getBookComment() +" , "+ book_comment);
        }
        borrowedRepository.save(borrowed);

        return borrowed;
    }
    /**
     * @author Clelia
     * check if the book with the id is reserved by me
     * */
    public Boolean checkIfBookedByMe(int bookId, Authentication authentication) {
        Book book = bookRepository.findById(bookId).get();
        List<Borrowed> allBorrowsOfBook = borrowedRepository.findByBook(book);
        // get the last entry ( newest one added)
        Borrowed borrowed = allBorrowsOfBook.get(allBorrowsOfBook.size()-1);
        User user = findAuthenticatedUser(authentication);
        if(borrowed.getUser().getId() == user.getId()) {
            return true;
        } else
            return false;
    }

    /**
     * @author Clelia
     * get all comments on a booking
     * */
    public List<Borrowed> allCommentsOnBook(int bookId) {
        Book book = bookRepository.findById(bookId).get();
        List<Borrowed> allBorrowsOfBook = borrowedRepository.findByBook(book);
        List<Borrowed> allWithComments = new ArrayList<>();
        for (Borrowed borrowed: allBorrowsOfBook) {
            if(borrowed.getBookComment() != null) {
                allWithComments.add(borrowed);
            }
        }
        return allWithComments;
    }

    /**
     * @author Clelia
     * give the user back based on authentication
     * */
    public User findAuthenticatedUser(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        userDetails.getUserId();
        return userRepository.findById(userDetails.getUserId());
    }


    public void returnBook(int bookId) {
        Book book = bookRepository.findById(bookId).get();
        book.setCurrentlyBorrowed(false);
        bookRepository.save(book);
    }
}

