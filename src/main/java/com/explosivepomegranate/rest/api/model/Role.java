package com.explosivepomegranate.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * author: Clelia
 * Role model, variables defined and methods generated
 * */
@Entity
public class Role {
    @Id
    private int role_id;
    private String name;
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public int getRole_id() {
        return role_id;
    }

    public String getName() {
        return name;
    }
}
