package com.explosivepomegranate.rest.api.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {
    @Id @GeneratedValue
    private int author_id;
    private String author_firstname;
    private String author_lastname;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authors", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
//Salvatore - connects the Book table with the Author table

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_firstname() {
        return author_firstname;
    }

    public void setAuthor_firstname(String author_firstname) {
        this.author_firstname = author_firstname;
    }

    public String getAuthor_lastname() {
        return author_lastname;
    }

    public void setAuthor_lastname(String author_lastname) {
        this.author_lastname = author_lastname;
    }


}
