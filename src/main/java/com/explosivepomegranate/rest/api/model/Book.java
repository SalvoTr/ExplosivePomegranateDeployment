package com.explosivepomegranate.rest.api.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;
    private String ISBN;
    private String title;
    private String description;
    private int year;
    private List<Book> books;

    //Salvatore - connects the Book table with the Category table
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_category",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> belongsToCategory; //List of categories the book belongs to - required for @ManyToMany

    //Salvatore - connects the Book table with the Author table
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> createdByAuthors; //List of all authors that have written this book - required for @ManyToMany


    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int id) {
        this.book_id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Category> getBelongsToCategory() {
        return belongsToCategory;
    }

    public void setBelongsToCategory(List<Category> belongsToCategory) {
        this.belongsToCategory = belongsToCategory;
    }

    public List<Author> getCreatedByAuthors() {
        return createdByAuthors;
    }

    public void setCreatedByAuthors(List<Author> createdByAuthors) {
        this.createdByAuthors = createdByAuthors;
    }
}
