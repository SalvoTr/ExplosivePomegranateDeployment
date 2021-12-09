package com.explosivepomegranate.rest.api.controller;

import com.explosivepomegranate.rest.api.model.*;
import com.explosivepomegranate.rest.api.service.BookService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController //Book Endpoint
public class BookController {

    @Autowired
    BookService bookService;

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
     * @author: Clelia
     * try to add a new book, body expected to be json format
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
     * @author: Salvatore
     * returns list of all borrowed books (UC11)
     * */
    @GetMapping (path = "/allBorrowed", produces = "application/json")
    public List<Borrowed> getBorrowed() { return bookService.getAllBorrowed(); }

    /**
     * @author: Clelia
     * save new book (UC10)
     * */
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
