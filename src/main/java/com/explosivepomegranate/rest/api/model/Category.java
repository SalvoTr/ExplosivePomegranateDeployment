package com.explosivepomegranate.rest.api.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id @GeneratedValue
    private int category_id;
    private String category_name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Book> books;

    public List<Book> getBooks() {
       return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    }
