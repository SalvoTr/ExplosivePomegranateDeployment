package com.explosivepomegranate.rest.api.model;

import javax.persistence.*;

@Entity
public class Login {

    private @Id
    @Column(name="user_id") int id;
    private String password;

    @OneToOne
    @MapsId
    @JoinColumn(name="user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
