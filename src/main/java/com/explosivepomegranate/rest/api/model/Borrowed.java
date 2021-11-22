package com.explosivepomegranate.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Borrowed {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowed_id;
    private int user_id;
    private int book_id;
    private Date startDate;
    private Date initialEndDate;
    private Date extendEndDate;
    private String bookComment;
    private boolean bookStatus;

    @ManyToOne @JsonIgnore
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public int getBorrowed_id() {
        return borrowed_id;
    }

    public void setBorrowed_id(int borrowed_id) {
        this.borrowed_id = borrowed_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getInitialEndDate() {
        return initialEndDate;
    }

    public void setInitialEndDate(Date initialEndDate) {
        this.initialEndDate = initialEndDate;
    }

    public Date getExtendEndDate() {
        return extendEndDate;
    }

    public void setExtendEndDate(Date extendEndDate) {
        this.extendEndDate = extendEndDate;
    }

    public String getBookComment() {
        return bookComment;
    }

    public void setBookComment(String bookComment) {
        this.bookComment = bookComment;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }
}
