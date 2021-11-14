package com.explosivepomegranate.rest.api.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {
    @Id @GeneratedValue
    private int author_id;
    private String author_firstname;
    private String author_lastname;

    //Salvatore - connects the Book table with the Author table
    @ManyToMany(mappedBy = "createdByAuthors")
//    @JoinTable(name = "book_author",
//            joinColumns = @JoinColumn(name = "author_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> wroteBooks; //List of books the author has written - required for @ManyToMany


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

    public List<Book> getWroteBooks() {
        return wroteBooks;
    }

    public void setWroteBooks(List<Book> wroteBooks) {
        this.wroteBooks = wroteBooks;
    }
}
