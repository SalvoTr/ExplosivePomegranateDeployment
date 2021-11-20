package com.explosivepomegranate.rest.api.controller;

import com.explosivepomegranate.rest.api.model.Author;
import com.explosivepomegranate.rest.api.model.Book;
import com.explosivepomegranate.rest.api.model.Category;
import com.explosivepomegranate.rest.api.repository.AuthorRepository;
import com.explosivepomegranate.rest.api.repository.BookRepository;
import com.explosivepomegranate.rest.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired // This means to get the bean called bookRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * @author: Salvatore
     *
     * */
    @GetMapping(path="/allCategories", produces = "application/json")
    public List<Category> getCategories() { return categoryRepository.findAll(); }


    /**
     * @author: Salvatore
     *
     * */
    @GetMapping(path="/allAuthors", produces = "application/json")
    public List<Author> getAuthors() { return authorRepository.findAll(); }


    /**
     * @author: Salvatore
     *
     * */
    @GetMapping(path="/allBooks",  produces = "application/json")
    public List<Book> getBooks() { return bookRepository.findAll(); }



    /**
     * @author Clelia
     * add a new book --------still a draft, model is still incomplete and this method will require some work
     * */
    @PostMapping(path = "/addBook")
    public @ResponseBody Book addNewBook(@RequestBody Book sendBookInfo) {
        Book book = new Book();
        book.setISBN(sendBookInfo.getISBN());
        book.setTitle(sendBookInfo.getTitle());
        book.setDescription(sendBookInfo.getDescription());
        book.setYear(sendBookInfo.getYear());

        bookRepository.save(book);
        return book;
    }

    /**
     *@author Sonja
     *UC6 find book by id
     *@return Book object found with given id
     **/
    @GetMapping (path="/bookInfo/{book_id}")
    public @ResponseBody
    Book getBookByID (@PathVariable (value="book_id") String bookId){
        //todo error handling?
        return bookRepository.findById(Integer.parseInt(bookId)).get();
    }
}
