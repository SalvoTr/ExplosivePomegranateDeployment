package com.explosivepomegranate.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Borrowed {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowed_id;
    private Date startDate;
    private Date initialEndDate;
    private Date extendEndDate;
    private String bookComment;
    private boolean bookStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public int getBorrowed_id() {
        return borrowed_id;
    }

    public void setBorrowed_id(int borrowed_id) {
        this.borrowed_id = borrowed_id;
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
