package com.explosivepomegranate.rest.api.controller;

import com.explosivepomegranate.rest.api.model.Author;
import com.explosivepomegranate.rest.api.model.Book;
import com.explosivepomegranate.rest.api.model.Borrowed;
import com.explosivepomegranate.rest.api.model.Category;
import com.explosivepomegranate.rest.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class BookController {

@Autowired
    BookService bookService;

    /**
     * @author: Salvatore
     *
     * */
    @GetMapping(path="/allCategories", produces = "application/json")
    public List<Category> getCategories() {
        return bookService.getAllCategories();
    }

    /**
     * @author: Salvatore
     *
     * */
    @GetMapping(path="/allAuthors", produces = "application/json")
    public List<Author> getAuthors() { return bookService.getAllAuthors(); }


    /**
     * @author: Salvatore
     *
     * */
    @GetMapping(path="/allBooks",  produces = "application/json")
    public List<Book> getBooks() { return bookService.getAllBooks(); }

    /**
     * @author: Clelia
     * */
    @PostMapping(path = "/addBook")
    public @ResponseBody ResponseEntity<Book> addNewBook(@RequestBody Book sendBookInfo) {
        Book book = null;
        try {
            book = bookService.addNewBook(sendBookInfo);
        } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return ResponseEntity.accepted().body(book);
    }

    /**
     *@author Sonja
     *UC6 find book by id
     *@return Book object found with given id
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
     * @author: Salvatore
     *
     * */
    @GetMapping (path = "/allBorrowed", produces = "application/json")
    public List<Borrowed> getBorrowed() { return bookService.getAllBorrowed(); }
}
