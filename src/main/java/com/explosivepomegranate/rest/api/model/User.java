package com.explosivepomegranate.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int id;
    private String firstname;
    private String lastname;
    private String email;

    //Connects the user with borrowed books
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Borrowed> borrowers;

    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    private Role role;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Login login;

    public User(int id, String firstname, String lastname, String email, Role role, Login login) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Borrowed> getBorrowers() {
        return borrowers;
    }

    public void setBorrowers(List<Borrowed> borrowers) {
        this.borrowers = borrowers;
    }
}
