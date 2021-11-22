package com.explosivepomegranate.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;
    private String ISBN;
    private String title;
    private String description;
    private int year;

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

    public boolean isCurrentlyBorrowed() {
        return currentlyBorrowed;
    }

    public void setCurrentlyBorrowed(boolean currentlyBorrowed) {
        this.currentlyBorrowed = currentlyBorrowed;
    }
}
