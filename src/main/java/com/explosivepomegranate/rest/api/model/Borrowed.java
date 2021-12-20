package com.explosivepomegranate.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "borrowed")
public class Borrowed {

    //with camel case initially didn't work - had to change names here and in SQL with underscores
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id", nullable = false)
    private int id;
    private Date start_date;
    private Date init_end_date;
    private String book_comment;

    //Connects to book
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id")
    private Book book;

    //Connects to user
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE) @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setBorrowed_id(int borrow_id) {
        this.id = borrow_id;
    }

    public Date getStartDate() {
        return start_date;
    }

    public void setStartDate(Date start_date) {
        this.start_date = start_date;
    }

    public Date getInitEndDate() {
        return init_end_date;
    }

    public void setInitEndDate(Date init_end_date) {
        this.init_end_date = init_end_date;
    }

    public String getBookComment() {
        return book_comment;
    }

    public void setBookComment(String book_comment) {
        this.book_comment = book_comment;
    }

    public boolean isBookStatus() {
        return this.book.isCurrentlyBorrowed();
    }

    public void setBookStatus(boolean book_status) {
        this.book.setCurrentlyBorrowed(book_status);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getBookIdFromBorrowed(){ return book.getBook_id();}
}
