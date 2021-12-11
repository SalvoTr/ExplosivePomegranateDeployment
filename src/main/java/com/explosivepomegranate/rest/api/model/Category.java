package com.explosivepomegranate.rest.api.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private int id;
    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories", cascade = {CascadeType.PERSIST, CascadeType.MERGE}) @JsonIgnore
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


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String category_name) {
        this.categoryName = category_name;
    }

    }
