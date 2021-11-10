package com.explosivepomegranate.rest.api.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id @GeneratedValue
    private int category_id;
    private String category_name;
    //Salvatore - connects the Book table with the Category table
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> booksInCategory; //List of all books written in this category - required for @ManyToMany


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

    public List<Book> getBooksInCategory() {
        return booksInCategory;
    }

    public void setBooksInCategory(List<Book> booksInCategory) {
        this.booksInCategory = booksInCategory;
    }
}
