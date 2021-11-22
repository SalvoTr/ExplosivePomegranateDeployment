package com.explosivepomegranate.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String firstname;
    private String lastname;
    private String email;

    @OneToMany(mappedBy="user")
    private List<Borrowed> borrowers;

    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    private Role role;

    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Login login;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "borrowers", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Book> books;

    public User(int user_id, String firstname, String lastname, String email, Role role, Login login) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.login = login;
    }

    public User() {

    }

    public User(String email, String password) {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Login getLogin() { return login; }

    public void setLogin(Login login){ this.login = login; }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
