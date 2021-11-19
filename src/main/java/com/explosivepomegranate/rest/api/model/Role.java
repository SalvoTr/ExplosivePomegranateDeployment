package com.explosivepomegranate.rest.api.model;

import javax.persistence.Entity;
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
    @OneToMany(mappedBy = "role")
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
