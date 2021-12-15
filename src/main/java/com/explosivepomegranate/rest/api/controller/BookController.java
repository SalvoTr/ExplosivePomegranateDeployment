package com.explosivepomegranate.rest.api.controller;

import com.explosivepomegranate.rest.api.model.*;
import com.explosivepomegranate.rest.api.service.BookService;
import com.explosivepomegranate.rest.api.service.BorrowedService;
import com.explosivepomegranate.rest.api.service.CustomUserDetails;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController //Book Endpoint
public class BookController {

    @Autowired
    BookService bookService;
@Autowired
    BorrowedService borrowedService;

    /**
     * @author: Salvatore
     * returns list of all categories (UC5)
     * */
    @GetMapping(path="/allCategories", produces = "application/json")
    public List<Category> getCategories() {
        return bookService.getAllCategories();
    }

    /**
     * @author: Salvatore
     * returns list of all authors (UC5)
     * */
    @GetMapping(path="/allAuthors", produces = "application/json")
    public List<Author> getAuthors() { return bookService.getAllAuthors(); }


    /**
     * @author: Salvatore
     * returns list of all books (UC5/6)
     * */
    @GetMapping(path="/allBooks",  produces = "application/json")
    public List<Book> getBooks() { return bookService.getAllBooks(); }

    /**
     * @author: Salvatore
     * Fetches all books from a given category ID(UC5)
     * */
    @GetMapping (path="/categoryBooks/{category_id}", produces = "application/json")
    public List<Book> getBookByCategory(@PathVariable(value = "category_id") String categoryId){
        return bookService.getBookByCategory(Integer.parseInt(categoryId));
    }

    /**
     * @author: Salvatore
     * Fetches all books from a given author ID(UC5)
     * */
    @GetMapping (path="/authorBooks/{author_id}", produces = "application/json")
    public List<Book> getBookByAuthor(@PathVariable(value = "author_id") String authorId){
        return bookService.getBookByAuthor(Integer.parseInt(authorId));
    }


    /**
     * @author Sonja
     * find book by id (UC6)
     * @return Book object found with given id
     **/
    @GetMapping (path="/bookInfo/{book_id}")
    public @ResponseBody
    ResponseEntity<Book> getBookByID (@PathVariable (value="book_id") String bookId){
        //todo error handling?
        Book book = null;
        try {
            book = bookService.getBookByID(Integer.parseInt(bookId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok(book);
    }

    /**
     * @author Sonja
     * UC7 reserve book
     */
    @PostMapping(path = "/reserveBook/{book_id}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Borrowed> reserveBook( @PathVariable(value = "book_id") String bookId, Authentication authentication) {
        Borrowed borrow;
        try {
            borrow = borrowedService.reserveBook(Integer.parseInt(bookId), authentication);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return ResponseEntity.accepted().body(borrow);
    }

    /**
     * @author Sonja
     * UC8 my borrowed books
     */

    @GetMapping(path = "/myBorrows")
    public List<Borrowed> myBorrows(Authentication authentication) {
        return borrowedService.getMyBorrows(authentication);
    }

    /**
     * @author: Salvatore
     * returns list of all borrowed books (UC11)
     * */
    @GetMapping (path = "/allBorrowed", produces = "application/json")
    public List<Borrowed> getBorrowed() { return borrowedService.getAllBorrowed();}

    /**
     * @author Sonja
     * UC18 add a comment to a book
     **/

 /*   @PostMapping(path = "/addComment/{book_id}")
    public @ResponseBody
    ResponseEntity<Borrowed> addNewComment(@RequestBody String addComment, //Authentication authentication
                                           @PathVariable(value = "book_id") String bookId) {
        Borrowed book_comment;
        try {
            book_comment = borrowedService.addNewComment(addComment, Integer.parseInt(bookId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return ResponseEntity.accepted().body(book_comment);
    }*/

    /**
     * @author: Clelia
     * save new book (UC10)
     * */
    @Secured("ROLE_ADMIN")
    @PostMapping (path = "/newBook", produces = "application/json")
    public ResponseEntity<JsonNode> postNewBook(@RequestBody JsonNode jsonNode) {
        try {
            bookService.saveNewBook(jsonNode);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().body(jsonNode);
    }
}


