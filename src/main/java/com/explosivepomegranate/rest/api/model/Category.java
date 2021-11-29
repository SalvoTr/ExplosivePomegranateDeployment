package com.explosivepomegranate.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id", nullable = false)
    private int id;
    private String category_name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Book> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //Getters and Setters
    public List<Book> getBooks() {
       return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    }
