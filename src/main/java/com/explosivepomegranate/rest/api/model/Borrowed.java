package com.explosivepomegranate.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "borrowed")
public class Borrowed {

    //with camel case initially didn't work - had to change names here and in SQL with underscores
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrow_id;
    private Date start_date;
    private Date init_end_date;
    private Date extend_end_date;
    private String book_comment;
    private boolean book_status ;

    //Connects to book
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    //Connects to user
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public int getBorrow_id() {
        return borrow_id;
    }

    public void setBorrowed_id(int borrow_id) {
        this.borrow_id = borrow_id;
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

    public Date getExtendEndDate() {
        return extend_end_date;
    }

    public void setExtendEndDate(Date extend_end_date) {
        this.extend_end_date = extend_end_date;
    }

    public String getBookComment() {
        return book_comment;
    }

    public void setBookComment(String book_comment) {
        this.book_comment = book_comment;
    }

    public boolean isBookStatus() {
        return book_status;
    }

    public void setBookStatus(boolean book_status) {
        this.book_status = book_status;
    }
}
